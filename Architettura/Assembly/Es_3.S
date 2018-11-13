.data
  n: .word 5
  v: .skip 20  ;;5*4 byte riservati

  base: .word 3
  argomento: .word 9

  dim: .word 5
  vec: .word 1, 2, 3, 4, 5

  dim_v: .word 9
  vec_v: .word 1, 2, 3, 4, 5, 6, 7, 8, 9
  p:     .word 2

  vet_crivello: .skip 64
  dim_crivello: .word 16

.text
  ldr r0, =v
  ldr r1, =n
  ldr r1, [r1]
  ;bl enumera            ;Es 3.1

  ldr r0, =base
  ldr r1, =argomento
  ldr r0, [r0]
  ldr r1, [r1]
  ;bl logaritmo          ;Es 3.2

  ldr r0, =vec
  ldr r1, =dim
  ldr r1, [r1]
  ;bl zera_pari         ;Es 3.3

  ldr r0, =vec_v
  ldr r1, =dim_v
  ldr r1, [r1]
  ldr r2, =p
  ldr r2, [r2]
  ;bl elimina_multiplo   ;Es 3.4

  ldr r0, =vet_crivello
  ldr r1, =dim_crivello
  ldr r1, [r1]

  bl crivello_eratostene ;Es 3.5

  swi 0x11

;;r0: indirizzo della zona di memoria
;;r1: dimensione della zona di memoria in word (Es:5)
enumera:
  stmfd sp!, {lr, r4-r9}
  mov r4, #0
  while: cmp r4, r1
         strlt r4, [r0, r4, lsl #2]
         addlt r4, r4, #1
         blt while
  ldmfd sp!, {lr, r4-r9}
  mov pc, lr

;;r0: la base del logaritmo
;;r1: l'argomento del logaritmo
logaritmo:
  stmfd sp!, {lr, r4-r9}
  mov r2, #1 ;contatore
  whilelog:
    mov r3, r0
    mul r0, r3, r0     ;accumulo le moltiplicazioni della base
    add r2, r2, #1
    cmp r0, r1         ;n > argomento
      bgt end
    b whilelog
  end:
  mov r0, r2
  ldmfd sp!, {lr, r4-r9}
  mov pc,lr

;;r0: indirizzo del vettore
;;r1 dimensione del vettore in word
  zera_pari:
    ldr r3, [r0, r2, lsl #2]
    ands r3, r3, #1
    streq r5, [r0, r2, lsl #2]
    cmp r2, r1
      add r2, r2, #1
      bne zera_pari
  mov pc, lr

;;r0: indirizzo base del vettore
;;r1: dimensione del vettore
;;r2: indice i cui multipli verranno eliminati
elimina_multiplo:
  stmfd sp!, {lr, r3-r9}
  mov r4, #2
  ciclo_:
    mul r3, r2, r4    ;;r3 sarà l'indice da zerare
    cmp r3, r1        ;;Controllo di non "sforare" il vettore
      bgt end_c
    mov r5, #0
    str r5, [r0, r3, lsl #2]  ;;Zero il valore in una posizione multiplo di r2
    add r4, r4, #1
    b ciclo_
  end_c:
  ldmfd sp!, {lr, r3-r9}
  mov pc, lr


;;r0: indirizzo base del vettore
;;r1: dimensione del vettore
crivello_eratostene:
  stmfd sp!, {lr, r4-r9}
  bl enumera              ;;Genero il vettore enumerato
  str r4, [r0, #4]        ;;Zero il valore 1
  mov r2, #2              ;;Contatore
  while_cr:
  cmp r2, r1
    blne elimina_multiplo   ;;elimino i multipli
    addne r2, r2, #1        ;;aggiorno il contatore
    bne while_cr
  ldmfd sp!, {lr, r4-r9}
  mov pc, lr

.end
