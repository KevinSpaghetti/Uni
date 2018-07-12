;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |5|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define manhattan-3d
  (lambda (i j k)
    (if (or (and (= i 0) (= j 0)) (and (= j 0) (= k 0)) (and (= i 0) (= k 0)))
        1
        (cond
          [(and (= i 0) (= j 0) (= k 0)) 0]
          [(= i 0)                (+ (manhattan-3d i (- j 1) k) (manhattan-3d i j (- k 1)))]
          [(= j 0)                (+ (manhattan-3d (- i 1) j k) (manhattan-3d i j (- k 1)))]
          [(= k 0)                (+ (manhattan-3d (- i 1) j k) (manhattan-3d i (- j 1) k))]
          [else (+ (manhattan-3d (- i 1) j k) (manhattan-3d i (- j 1) k) (manhattan-3d i j (- k 1)))]
          )
        )
    )
  )
  

(manhattan-3d 0 0 7)
(manhattan-3d 2 0 2)
(manhattan-3d 1 1 1)
(manhattan-3d 1 1 5)
(manhattan-3d 2 3 1)
(manhattan-3d 2 3 3)
