.data
  ;vett: .word 1, 2, 3, 4, 5
  ;dim:  .word 5

  matx: .word 4, 4, 2, 1, 3, 4, 2, 4, 3

  mat_dim: .word 3

  cr:   .asciiz "\n"

  .equ SWI_MAlloc, 0x12
  .equ SWI_wrInt,  0x6b
  .equ SWI_wrStr,  0x69

.text

  ;ldr r0, =vett
  ;ldr r1, =dim
  ;ldr r1,[r1]

  ;bl init_lista
  ;bl pari_dispari
  ;mov r6,r0
  ;mov r7,r1
  ;bl visualizza_lista
  ;mov r0,r7
  ;bl visualizza_lista

  ldr r0, =matx
  ldr r1, =mat_dim
  ldr r1, [r1]

  bl check_matrix

  swi 0x11

  ;;r0 : indirizzo della matrice quadrata
  ;;r1 : dimensione della matrice
  check_matrix:
    stmfd sp!, {r4-r9}
      ;;posizione elemento (i:j) = (dim * i)+j
      ;;trasposizione elemento (j:i) = (dim * j)+i
      ;;r2,r3 indici di righe e colonne
      while_righe:
        while_colonne:
          mul r5, r2, r1
          add r5, r5, r3
          ldr r5, [r0, r5, lsl #2]
          mul r6, r3, r1
          add r6, r6, r2
          ldr r6, [r0, r6, lsl #2]
          cmp r5,r6 ;;se non sono uguali esco e ritorno falso
            movne r0,#0
            bne exit_no
          add r3, r3, #1
          cmp r3, r1
        bne while_colonne
        mov r3,#0
        add r2, r2, #1
        cmp r2, r1
      bne while_righe
      mov r0, #1  ;;se arrivo a questo punto senza uscire la matrice è simmetrica (0 chiamate a exit_no)
      exit_no:
    ldmfd sp!, {r4-r9}
    mov pc,lr

  ;;r0 : indirizzo della lista di interi
  ;;r0 : lista risultato dei pari
  ;;r1 : lista risultato dei dispari
  pari_dispari:
    stmfd sp!,{r4-r9}
    mov r4,r0
    ldr r5, [r4, #4]  ;;carico il dato
    mov r0, #8        ;;4byte per l'indirizzo 4 per il dato
    swi SWI_MAlloc
    str r5, [r0, #4] ;;il numero
    ands r3, r5, #1         ;;se è pari (and = 0)
      streq r8, [r0]     ;;Collego l'elemento alla lista dei pari
      moveq r8, r0
      strne r9, [r0]     ;;Collego l'elemento alla lista dei dispari
      movne r9, r0
    ldr r0, [r4]     ;;carico il prossimo indirizzo
    cmp r0, #0
      bne pari_dispari
    mov r0,r8
    mov r1,r9
    ldmfd sp!,{r4-r9}
    mov pc,lr

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
        stmfd sp!,{r4-r9}
        mov r2, r0
        ldr r1, [r2, #4] ;;carico il dato
        mov r0, #1
        swi SWI_wrInt
        ldr r1, =cr
        swi SWI_wrStr
        ldr r0, [r2]     ;;carico il prossimo indirizzo
        cmp r0, #0
          bne visualizza_lista
        ldmfd sp!,{r4-r9}
      mov pc,lr



.end
