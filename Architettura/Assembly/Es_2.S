.data
  vect: .word 0, 0, 0, 0
  quad: .word 1, 2, 3, 4
  perm: .word 3, 5, 7, 9
  m: .word 20
  n: .word 5

.text
  ;Es 2.1
  ldr r1, =vect ; address Index
  mov r10, #0   ; cycle Index
  if: cmp r10, #4
    beq then
    mov r9, r10, lsl #2
    add r10, r10, #1
    str r10, [r1, r9]
    b if
  then:

  ;Es 2.2
  ldr r1, =quad ; address Index
  mov r10, #0   ; cycle Index
  ifb: cmp r10, #16
    beq thenb
    ldr r2, [r1, r10]
    mov r3, #0
    mov r6, #1
    ifi: cmp r3, #4
        beq theni
        mul r6, r2, r6
        add r3, r3, #1
        b ifi
      theni:
    str r6, [r1, r10]
    add r10, r10, #4
    b ifb
  thenb:

  ;Es 2.3
  ldr r1, =perm ;first address
  add r2, r1, #4
  mov r10, #0
  ifc: cmp r10, #12
    beq thenc
      ldr r4, [r1, r10]
      ldr r5, [r2, r10]
      str r4, [r2, r10]
      str r5, [r1, r10]
      add r10, r10, #4

    b ifc
  thenc:

  ;Es 2.4

  ldr r0, =m
  ldr r0, [r0] ;Dividendo Originale
  ldr r1, =n
  ldr r1, [r1] ;Divisore

  mov r4, r0   ;Dividendo Modificabile
  mov r2, #0   ;Quoziente
  while:
    sub r4, r4, r1
    cmp r4, #0         ;N - dividendo >= 0
      blt end
      add r2, r2, #1   ;Aggiungi solo se il risultato è positivo
    b while
  end:
  mul r3, r2, r1
  sub r1, r0, r3 ; Resto = Dividendo - (Divisore*Quoziente)
  mov r0, r2

  swi 0x11
.end
