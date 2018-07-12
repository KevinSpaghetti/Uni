;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |4|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #t #t none #f () #f)))

(define btr-sum
  (lambda (str1 str2)
    (normalized-btr (btr-carry-sum str1 str2 #\.))
    )
  )

(define btr-carry-sum
  (lambda (str1 str2 carry)
    (let ([str1_c (if (= (string-length str1) 0) #\. (string-ref str1 0))]
          [str2_c (if (= (string-length str2) 0) #\. (string-ref str2 0))])
      (if (or (> (string-length str1) 1) (> (string-length str2) 1))
           (string-append
            (btr-carry-sum (head str1) (head str2) (btr-carry (lsd str1) (lsd str2) carry))
            (string (btr-digit-sum (lsd str1) (lsd str2) carry))
             )
           
            (string (btr-digit-sum str1_c str2_c carry))
           )
    )
          ))
      
  
    
  

(define normalized-btr ;Rimuove gli eventuali punti davanti alla stringa
  (lambda (btr)
    (if (> (string-length btr) 1)
        (if (char=? (string-ref btr 0 ) #\.) 
            (normalized-btr (substring btr 1 (string-length btr)))
            btr
            )
        btr
        )
    )
  )

(define lsd ; Prende solo l'ultimo carattere
  (lambda (btr)
    (if(= (string-length btr) 0)
       #\.
       (string-ref (substring btr (- (string-length btr) 1))0)
       )
    )
  )
(define head ; prende tutti tranne l'ultimo carattere
  (lambda (btr)
    (if(= (string-length btr) 0)
       ""
       (substring btr 0 (- (string-length btr) 1 ))
       )
    )
  )

(define btr-digit-sum                    ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\.)
                        ((char=? c #\.)  ; - - .
                         #\+)
                        ((char=? c #\+)  ; - - +
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\+)
                        ((char=? c #\.)  ; - . .
                         #\-)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\+)
                        ((char=? c #\.)  ; . - .
                         #\-)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\+)
                        ((char=? c #\+)  ; . + +
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\+)
                        ((char=? c #\+)  ; + . +
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\+)
                        ((char=? c #\.)  ; + + .
                         #\-)
                        ((char=? c #\+)  ; + + +
                         #\.)))))
          )))

(define btr-carry                   ; val:     carattere +/./-
  (lambda (u v c)                        ; u, v, c: caratteri +/./-
    (cond ((char=? u #\-)                ; u v c
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; - - -
                         #\-)
                        ((char=? c #\.)  ; - - .
                         #\-)
                        ((char=? c #\+)  ; - - +
                         #\.)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; - . -
                         #\-)
                        ((char=? c #\.)  ; - . .
                         #\.)
                        ((char=? c #\+)  ; - . +
                         #\.)))
                 ((char=? v #\+)         ; - + c
                  #\.)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-)  ; . - -
                         #\-)
                        ((char=? c #\.)  ; . - .
                         #\.)
                        ((char=? c #\+)  ; . - +
                         #\.)))
                 ((char=? v #\.)         ; . . c
                  #\.)
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; . + -
                         #\.)
                        ((char=? c #\.)  ; . + .
                         #\.)
                        ((char=? c #\+)  ; . + +
                         #\+)))))
          ((char=? u #\+)
           (cond ((char=? v #\-)         ; + - c
                  #\.)
                 ((char=? v #\.)
                  (cond ((char=? c #\-)  ; + . -
                         #\.)
                        ((char=? c #\.)  ; + . .
                         #\.)
                        ((char=? c #\+)  ; + . +
                         #\+)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-)  ; + + -
                         #\.)
                        ((char=? c #\.)  ; + + .
                         #\+)
                        ((char=? c #\+)  ; + + +
                         #\+)))))
          )))

(btr-sum "-+--" "+")
(btr-sum "-+--" "-")
(btr-sum "+-.+" "-+.-")
(btr-sum "-+--+" "-.--")
(btr-sum "-+-+." "-.-+")
(btr-sum "+-+-." "+.+-")