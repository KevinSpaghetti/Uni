;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname |1|) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))

(define SINGOLARE_MASCHILE 0)
(define SINGOLARE_FEMMINILE 1)
(define PLURALE_MASCHILE 2)
(define PLURALE_FEMMINILE 3)

(define ARE 0)
(define ERE 1)
(define IRE 2)

(define tipo
  (λ (parola)
    (cond
      [(char=? (string-ref parola (- (string-length parola) 1)) #\o) SINGOLARE_MASCHILE]
      [(char=? (string-ref parola (- (string-length parola) 1)) #\a) SINGOLARE_FEMMINILE]
      [(char=? (string-ref parola (- (string-length parola) 1)) #\i) PLURALE_MASCHILE]
      [(char=? (string-ref parola (- (string-length parola) 1)) #\e) PLURALE_FEMMINILE]
      )
    )
  )

(define tempo
  (λ (a_verbo)
    (cond
      [(char=? (string-ref a_verbo (- (string-length a_verbo) 3)) #\a) ARE]     ;are
      [(char=? (string-ref a_verbo (- (string-length a_verbo) 3)) #\e) ERE]     ;ere
      [(char=? (string-ref a_verbo (- (string-length a_verbo) 3)) #\i) IRE]     ;ire
    )
    )
  )
    
(define f_sostantivo
  (λ (a_sogg)
    (cond
      [(= (tipo a_sogg) SINGOLARE_MASCHILE)  (string-append "il "  a_sogg " ")]
      [(= (tipo a_sogg) SINGOLARE_FEMMINILE) (string-append "la "  a_sogg " ")]
      [(= (tipo a_sogg) PLURALE_MASCHILE)    (string-append "i  "  a_sogg " ")]
      [(= (tipo a_sogg) PLURALE_FEMMINILE)   (string-append "le "  a_sogg " ")]
      )
    )
  )

(define sostituisci
  (λ (parola range sost)
    (string-append (substring parola 0 (- (- (string-length parola) range) 1)) sost)
    )
  )

(define f_verbo
  (λ (a_verbo tipo)
    (cond
      [(and (= tipo SINGOLARE_MASCHILE) (= (tempo a_verbo) ARE))  (sostituisci a_verbo 1 " ")]
      [(and (= tipo SINGOLARE_MASCHILE) (= (tempo a_verbo) ERE))  (sostituisci a_verbo 2 "eva ")]
      [(and (= tipo SINGOLARE_MASCHILE) (= (tempo a_verbo) IRE))  (sostituisci a_verbo 2 "e ")]

      [(and (= tipo SINGOLARE_FEMMINILE) (= (tempo a_verbo) ARE)) (sostituisci a_verbo 1 " ")]
      [(and (= tipo SINGOLARE_FEMMINILE) (= (tempo a_verbo) ERE)) (sostituisci a_verbo 2 "eva ")]
      [(and (= tipo SINGOLARE_FEMMINILE) (= (tempo a_verbo) IRE)) (sostituisci a_verbo 2 "e ")]

      [(and (= tipo PLURALE_MASCHILE) (= (tempo a_verbo) ARE))    (sostituisci a_verbo 1 "no ")]
      [(and (= tipo PLURALE_MASCHILE) (= (tempo a_verbo) ERE))    (sostituisci a_verbo 0 "ono ")]
      [(and (= tipo PLURALE_MASCHILE) (= (tempo a_verbo) IRE))    (sostituisci a_verbo 0 "ono ")]

      [(and (= tipo PLURALE_FEMMINILE) (= (tempo a_verbo) ARE))   (sostituisci a_verbo 1 "no ")]
      [(and (= tipo PLURALE_FEMMINILE) (= (tempo a_verbo) ERE))   (sostituisci a_verbo 2 "ono ")]
      [(and (= tipo PLURALE_FEMMINILE) (= (tempo a_verbo) IRE))   (sostituisci a_verbo 2 "ono ")]
      )
  )
)

(define frase
  (λ (soggetto verbo oggetto)
     (string-append (f_sostantivo soggetto) (f_verbo verbo (tipo soggetto)) (f_sostantivo oggetto))
    )
  )


(frase "gatto" "cacciare" "topi")
(frase "mucca" "mangiare" "fieno")
(frase "sorelle" "leggere" "novella")
(frase "bambini" "amare" "favole")
(frase "musicisti" "suonare" "pianoforti")
(frase "cuoco" "friggere" "patate")
(frase "camerieri" "servire" "clienti")
(frase "mamma" "chiamare" "figlie")






