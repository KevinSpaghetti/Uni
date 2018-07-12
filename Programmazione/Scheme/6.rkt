;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |6|) (read-case-sensitive #t) (teachpacks ((lib "gluetiles.ss.txt" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ((lib "gluetiles.ss.txt" "installed-teachpacks")) #f)))
(set-tessellation-shift-step!)


(define L-tessellation
  (lambda (lato)
    (cond
      [(= lato 1) L-tile]
      [#T
       (glue-tiles
        (glue-tiles
         (glue-tiles
          (L-tessellation (/ lato 2))
          (shift-down (shift-right (L-tessellation (/ lato 2)) (/ lato 2)) (/ lato 2))
          )
         (shift-down (quarter-turn-left (L-tessellation (/ lato 2))) lato)
         )
        (shift-right (quarter-turn-right (L-tessellation (/ lato 2))) lato)
        )]
      )
    )
  )
  
  
(L-tessellation 16)