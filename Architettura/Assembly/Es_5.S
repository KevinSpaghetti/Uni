.data
  vett: .word 1, 2, 3, -1, -2
  dim:  .word 5

  cr:   .asciiz "\n"

  .equ SWI_MAlloc, 0x12
  .equ SWI_wrInt,  0x6b
  .equ SWI_wrStr,  0x69

.text

  ldr r0, =vett
  ldr r1, =dim
  ldr r1,[r1]

  bl init_lista

  ;;in r0 ora c'è l'indirizzo della lista

  bl visualizza_lista

  ldr r0, =vett
  ldr r1, =dim
  ldr r1,[r1]

  ;bl riordina_vettore

  swi 0x11

  ;;r0:indirizzo del vettore
  ;;r1:dimensione del vettore
  ;;ritorna in r0 l'indirizzo della lista
  init_lista:
    stmfd sp!,{r4-r9}
    mov r4,r0 ;;r4 contiene l'indirizzo del vettore
    subs r1, r1, #1
    while:
      ldr r5, [r4, r1, lsl #2] ;;carico in r5
      mov r0, #8        ;;4byte per l'indirizzo 4 per il dato
      swi SWI_MAlloc
      str r10, [r0]     ;;r10 inizialmente è 0, poi sarà l'indirizzo del successivo
      str r5,  [r0, #4] ;;salvo il dato all'indirizzo allocato
      subs r1, r1, #1
      mov r10, r0     ;;voglio ritornare in r10 l'indirizzo
      bpl while
    mov r0,r10
    ldmfd sp!,{r4-r9}
    mov pc,lr

  ;;r0: l'indirizzo della lista
  visualizza_lista:
      mov r2, r0
      ldr r1, [r2, #4] ;;carico il dato
      mov r0, #1
      swi SWI_wrInt
      ldr r1, =cr
      swi SWI_wrStr
      ldr r0, [r2]     ;;carico il prossimo indirizzo
      cmp r0, #0
        bne visualizza_lista
    mov pc,lr


  ;;r0:indirizzo del vettore
  ;;r1:dimensione del vettore
  riordina_vettore:
    stmfd sp!,{r4-r9}
    mov r4,r0
    sub r9,r1,#1
    bl init_lista ;;carico in una lista il vettore
    while_o:
      ldr r1,[r0, #4] ;;carico il numero
      cmp r1,#0       ;;controllo se il numero è negativo
      strlt r1, [r4, r8, lsl #2]  ;;se è negativo lo metto all'inizio
      addlt r8,r8,#1
      strpl r1, [r4, r9, lsl #2]  ;;se è positivo lo metto alla fine
      subpl r9,r9,#1
      ldr r0,[r0]
      cmp r0, #0
        bne while_o
    ldmfd sp!,{r4-r9}
    mov pc,lr
.end
