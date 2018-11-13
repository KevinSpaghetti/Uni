.data
  n: .word 5, 20, 30, 50
.text
      ldr r1, =n              ;;Somma
      ldr r12, [r1]
if:   cmp r10, #16
      beq then
        ldr r2, [r1, r10]
        add r0, r0, r2
        add r10, r10, #4
        b if
then: mov r1, r0, asr #2      ;;Media
      mov r7, #1
      mov r7, r7, lsl #10
      add r7, r7, #1
      mul r2, r12, r7         ;;(2^10+1) *n
      and r3, r12, #15        ;;Resto
      mov r4, r12, lsr #31    ;;Segno
  swi 0x11
.end
