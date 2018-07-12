;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |9|) (read-case-sensitive #t) (teachpacks ((lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ((lib "hanoi.ss" "installed-teachpacks")) #f)))
(define hanoi-moves
  (lambda (n)
    (hanoi-rec n 1 2 3)
    )
  )

(define hanoi-rec
  (lambda (n s d t)
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          )
        )
    )
  )

(define hanoi-disks
  (lambda (n k)
    (if (= k 0)
        (list (list 1 n) (list 2 0) (list 3 0))
        (hanoi-disks_ n k 1 2 3 0 0 0)
        )
    )
  )

(define hanoi-disks_ 
  (lambda (n k s d t s1 d1 t1)
    (cond
      [(= n 0) (list (list s s1) (list d d1) (list t t1))]
      [(< k (expt 2 (- n 1)))  (hanoi-disks_ (- n 1) k s t d (+ s1 1) t1 d1)]
      [(>= k (expt 2 (- n 1))) (hanoi-disks_ (- n 1) (- k (expt 2 (- n 1))) t d s t1 (+ d1 1) s1)]
      )
    )
  )

(define hanoi-picture
  (lambda (n k)
    (hanoi-pic_ n k 1 2 3 0 0 0 n (towers-background n))
   )
  )

(define hanoi-pic_ 
  (lambda (n k s d t s1 d1 t1 l image)
    (cond
      [(= n 0) image]
      [(< k (expt 2 (- n 1)))  (hanoi-pic_ (- n 1) k s t d (+ s1 1) t1 d1 l (above (disk-image n l s s1) image))]
      [(>= k (expt 2 (- n 1))) (hanoi-pic_ (- n 1) (- k (expt 2 (- n 1))) t d s t1 (+ d1 1) s1 l (above (disk-image n l d d1) image))]
      )
    )
  )


(hanoi-disks 3 0)
(hanoi-disks 3 1)
(hanoi-disks 3 2)
(hanoi-disks 3 3)
(hanoi-disks 3 4)
(hanoi-disks 3 5)
(hanoi-disks 3 6)
(hanoi-disks 3 7)
(hanoi-disks 5 13)
(hanoi-disks 15 19705)
(hanoi-disks 15 32767)

(hanoi-picture 5 0)
(hanoi-picture 5 13)
(hanoi-picture 5 22)
(hanoi-picture 5 31)
(hanoi-picture 15 19705)