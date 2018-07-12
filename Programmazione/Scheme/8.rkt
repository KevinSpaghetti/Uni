;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |8|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define primo?
  (lambda (n)
    (primo_ n (ceiling (/ n 2)))
    )
  )

(define primo_
  (lambda (n k)
    (cond
      [(= k 1) #T]
      [(= (remainder n k) 0) #F]
      [else (primo_ n (- k 1))]
      )
    )
  )

(define prime-factors
  (lambda (n)
    (prime-factors_ n 2 '())
    )
  )

(define prime-factors_
  (lambda (n k factors)
    (if (> n 2)
        (if (primo? k)
            (if (= (remainder n k) 0)
                (prime-factors_ (quotient n k) 2 (append factors (list k)))
                (prime-factors_ n (+ k 1) factors)
                )
            (prime-factors_ n (+ k 1) factors)
            )
        factors
        )
    )
  )
    
(define short-prime-factors
  (lambda (n)
    (short-prime-factors_ n 2 '())
    )
  )

(define short-prime-factors_
  (lambda (n k factors)
    (let ((previous
           (if (= (length factors) 0)
               0
               (list-ref factors (- (length factors) 1))
               )))
      (if (> n 2)
          (if (primo? k)
              (if (= (remainder n k) 0)
                  (if (= previous k)
                      (short-prime-factors_ (quotient n k) 2 factors)
                      (short-prime-factors_ (quotient n k) 2 (append factors (list k)))
                      )
                  (short-prime-factors_ n (+ k 1) factors)
                  )
              (short-prime-factors_ n (+ k 1) factors)
              )
          factors
          )
      )
    )
  )

(define power-prime-factors
  (lambda (n)
    (power-prime-factors_ (prime-factors n) 0 0 1 '())
    )
  )

;pos: posizione nella lista
;n: il precedente
;acc: il numero di fattori uguali
;ret: lista da ritornare
(define power-prime-factors_
  (lambda (factors pos n acc ret)
    (cond
      [(>= pos (length factors))          (append ret (list (list n acc)))]
      [(= pos 0)                          (power-prime-factors_ factors (+ pos 1) (list-ref factors 0) 1 ret)]
      [(= n (list-ref factors pos))       (power-prime-factors_ factors (+ pos 1) (list-ref factors pos) (+ acc 1) ret)]
      [(not (= n (list-ref factors pos))) (power-prime-factors_ factors (+ pos 1) (list-ref factors pos) 1 (append ret (list (list n acc))))]
      )
    )
  )




"1"
(prime-factors 7)
(prime-factors 9)
(prime-factors 28)
(prime-factors 39)
(prime-factors 540)
(prime-factors 1617)
"2"
(short-prime-factors 7)
(short-prime-factors 9)
(short-prime-factors 28)
(short-prime-factors 39)
(short-prime-factors 540)
(short-prime-factors 1617)
"3"
(power-prime-factors 7)
(power-prime-factors 9)
(power-prime-factors 28)
(power-prime-factors 39)
(power-prime-factors 540)
(power-prime-factors 1617)