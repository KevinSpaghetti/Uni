;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |2|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define croce
  (glue-tiles
   (glue-tiles larger-tile (shift-right smaller-tile 1.6))
   (shift-right (shift-down (half-turn (glue-tiles larger-tile (shift-right smaller-tile 1.6))) 0.8) 1.6)
   )
 )

(define quadrato
  (glue-tiles
   (glue-tiles
    (shift-down (quarter-turn-right larger-tile) 1.6)
    (shift-down (quarter-turn-right smaller-tile) 1.6)
    )
   (glue-tiles
    (shift-right (quarter-turn-left larger-tile) 0.8)
    (shift-down  (shift-right (quarter-turn-left smaller-tile) 4) 1.6)
   )
  )
 )

croce
quadrato