;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |3|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))
(define sign
  (λ (chr)
    (if (char=? chr #\-)
        -1
        1
        )
    )
  )

(define n-value
  (λ (cfr)
    (if (char=? cfr #\0)
        0
        1
        )
    )
  )

(define integer-part
  (λ (stringa)
    (if (> (string-length stringa) 1)
        (+ (* 2 (integer-part (substring stringa 0 (- (string-length stringa) 1)))) (n-value (string-ref stringa (- (string-length stringa) 1))))
        (n-value (string-ref stringa (- (string-length stringa) 1)))
        )
    )
  )



(define rational-c
  (λ (stringa k)
    (if (< k (string-length stringa))
        (+ (* (expt 2 (* -1 k)) (n-value (string-ref stringa (- k 1)))) (rational-c stringa (+ k 1)))
        (* (expt 2 (* -1 k)) (n-value (string-ref stringa (- k 1))))
        )
    )
  )

(define rational
  (λ (stringa)
    (rational-c stringa 1)
    )
  )

(define find-point 
  (lambda (str k)
    (if (or (= k (string-length str)) (char=? (string-ref str k) #\.))
        k
        (find-point str (+ k 1))
        ) 
    )
  )

(define bin-rep->integer
  (lambda (num_s)
    (* (sign (string-ref num_s 0)) 
       (+ 
        (if (or (char=? (string-ref num_s 0) #\+) (char=? (string-ref num_s 0) #\-))
            (integer-part (substring num_s 1 (find-point num_s 1)))
            (integer-part (substring num_s 0 (find-point num_s 1)))
            )
        (if (< (find-point num_s 0) (string-length num_s))
         (rational (substring num_s (+ (find-point num_s 0) 1) (string-length num_s)))  
         0)
         )
       )
    )
  )


;;;;Base arbitraria

(define n-value-for-base
  (λ (cfr base)
    (find-value-for-chr-in-representation cfr base 0)
    )
  )

(define find-value-for-chr-in-representation
  (lambda (charachter rep k)
    (if (char=? (string-ref rep k) charachter)
        k
        (find-value-for-chr-in-representation charachter rep (+ k 1))
        )
    )
  )

(define integer-part-for-base
  (λ (stringa base)
    (if (> (string-length stringa) 1)
        (+ (* (string-length base) (integer-part-for-base (substring stringa 0 (- (string-length stringa) 1)) base)) (n-value-for-base (string-ref stringa (- (string-length stringa) 1)) base))
        (n-value-for-base (string-ref stringa (- (string-length stringa) 1)) base)
        )
    )
  )



(define rational-c-for-base
  (λ (stringa k base)
    (if (< k (string-length stringa))
        (+ (* (expt (string-length base) (* -1 k)) (n-value-for-base (string-ref stringa (- k 1)) base)) (rational-c-for-base stringa (+ k 1) base))
        (* (expt (string-length base) (* -1 k)) (n-value-for-base (string-ref stringa (- k 1)) base))
        )
    )
  )

(define rational-for-base
  (λ (stringa base)
    (rational-c-for-base stringa 1 base)
    )
  )

(define rep->number
  (lambda (base num_s)
    (* (sign (string-ref num_s 0)) 
       (+ 
        (if (or (char=? (string-ref num_s 0) #\+) (char=? (string-ref num_s 0) #\-))
            (integer-part-for-base (substring num_s 1 (find-point num_s 1)) base)
            (integer-part-for-base (substring num_s 0 (find-point num_s 1)) base)
            )
        (if (= (find-point num_s 0) (string-length num_s))
            0.0
            (rational-for-base (substring num_s (+ (find-point num_s 0) 1) (string-length num_s)) base)  
            )
        )
       )
    )
  )


(bin-rep->integer "+1101")
(bin-rep->integer "0")
(bin-rep->integer "10110.011")
(bin-rep->integer "-0.1101001")


(rep->number "zu" "-uuzz")
(rep->number "0123" "+21.1")
(rep->number "01234" "-10.02")
(rep->number "0123456789ABCDEF" "0.A")
(rep->number "0123456789ABCDEF" "1CF.0")
