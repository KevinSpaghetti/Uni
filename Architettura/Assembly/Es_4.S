.data

  dim:  .word 5
  vett: .word 1, 2, 3, 4, 5

  space:  .asciiz " "
  filename: .asciiz "file"
  sorgente: .asciiz "src"
  destinazione: .asciiz "dest"

  .equ SWI_Open,   0x66
  .equ SWI_Close,  0x68
  .equ SWI_wrStr,  0x69
  .equ SWI_rdInt,  0x6c
  .equ SWI_wrInt,  0x6b
  .equ SWI_MAlloc, 0x12

.text

  ldr r0, =filename
  mov r1, #0
  swi SWI_Open

  ;bl carica_interi

  ldr r0, =filename
  mov r1, #1
  swi SWI_Open

  mov r9, r0  ;;memorizzo l'handle del file per poterlo chiudere

  ldr r1, =vett
  ldr r2, =dim
  ldr r2, [r2]

  ;bl salva_interi

  ldr r0, =sorgente  ;;apro il primo file in lettura
  mov r1, #0
  swi SWI_Open
  mov r5,r0

  ldr r0, =destinazione  ;;apro il secondo file in scrittura
  mov r1, #1
  
  swi SWI_Open
  mov r1,r0

  mov r0,r5
  bl copia_interi

  swi SWI_Close ;;chiudo il file
  mov r0,r9
  swi SWI_Close ;;chiudo il file
  mov r0,r1
  swi SWI_Close ;;chiudo il file
  end:
    swi 0x11


;;r0:handle del file
;;ritorna in r0 l'indirizzo della memoria allocata
;;ritorna in r1 la dimensione in byte della
;;memoria allocata
  carica_interi:
    stmfd sp!, {lr,r4-r9}
    mov r2,r0
    swi SWI_rdInt  ;;leggo il file per il primo numero
    mov r1, r0     ;;r1 contiene il numero dei numeri
    mov r0, r1, lsl #2
    swi SWI_MAlloc ;;Alloco la memoria per r0 num
    mov r3,r0      ;;Salvo l'indirizzo della memoria allocata
    while:
      cmp r6,r1     ;;r6 è il contatore
        mov r0,r2
        swi SWI_rdInt ;;leggo i numeri successivi
        str r0, [r3, r6, lsl #2]
        add r6,r6,#1
        bne while
    fine:
    mov r0,r3
    ldmfd sp!, {lr,r4-r9}
    mov pc, lr

;;r0: handle del file
;;r1: vettore
;;r2: dimensione
  salva_interi:
    stmfd sp!, {lr, r4-r9}
    mov r5,r0
    mov r8,r1
    mov r1,r2
    swi SWI_wrInt
    while_s:
          cmp r2,r7
          beq fine_s
          mov r0, r5
          ldr r1, =space
          swi SWI_wrStr
          mov r0, r5
          ldr r1, [r8, r7, lsl #2]
          swi SWI_wrInt
          add r7, r7, #1
        bne while_s
    fine_s:
    ldmfd sp!, {lr, r4-r9}
    mov pc,lr

;;r0: handle del file sorgente
;;r1: handle del file destinazione
  copia_interi:
    stmfd sp!, {lr, r4-r9}
    mov r4,r1
    bl carica_interi ;;in r0 ho l'indirizzo e in r1 ho la dim
    mov r2,r1
    mov r1,r0       ;;preparo i parametri per la procedura
    mov r0,r4
    bl salva_interi
    ldmfd sp!, {lr, r4-r9}
    mov pc,lr

.end
