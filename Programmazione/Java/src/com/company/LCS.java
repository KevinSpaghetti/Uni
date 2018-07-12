package com.company;
/*
 * Tecniche di memoization e programmazione dinamica applicate
 * al problema della sottosequenza comune piu' lunga
 *
 * Ultimo aggiornamento: 11/04/2014
 */


public class LCS {


/* Lunghezza della sottosequenza comune piu' lunga (LLCS)

  (define llcs     ; valore: intero
    (lambda (u v)  ; u, v: stringhe
      (cond ((or (string=? u "") (string=? v ""))
             0)
            ((char=? (string-ref u 0) (string-ref v 0))
             (+ 1 (llcs (substring u 1) (substring v 1))))
            (else
             (max (llcs (substring u 1) v) (llcs u (substring v 1))))
            )))
*/
  
// Traduzione in Java:
  
  public static int llcs( String u, String v ) {
  
    if ( u.equals("") || v.equals("") ) {
      return  0;
    } else if ( u.charAt(0) == v.charAt(0) ) {
      return  1 + llcs( u.substring(1), v.substring(1) );
    } else {
      return  Math.max( llcs(u.substring(1),v), llcs(u,v.substring(1)) );
    }
  }
  
  
// Versione che applica la tecnica di memoization:
  
  public static int llcsMem( String u, String v ) {
  
    int m = u.length();
    int n = v.length();
    
    int[][] answer = new int[ m+1 ][ n+1 ];
    
    for ( int i=0; i<=m; i=i+1 ) {
      for ( int j=0; j<=n; j=j+1 ) {
        answer[i][j] = UNKNOWN;
    }}
    return llcsRec( u, v, answer );
  }
  
  private static int llcsRec( String u, String v, int[][] answer ) {
  
    int i = u.length();
    int j = v.length();
    
    if ( answer[i][j] == UNKNOWN ) {
    
      if ( u.equals("") || v.equals("") ) {
        answer[i][j] = 0;
      } else if ( u.charAt(0) == v.charAt(0) ) {
        answer[i][j] = 1 + llcsRec( u.substring(1), v.substring(1), answer );
      } else {
        answer[i][j] = Math.max( llcsRec(u.substring(1),v,answer),
                                 llcsRec(u,v.substring(1),answer) );
    }}
    return answer[i][j];
  }
  
  private static final int UNKNOWN = -1;  // 0 e' un valore ammissibile
  
  
/* Sottosequenza comune piu' lunga (LCS)

  (define lcs      ; valore: stringa
    (lambda (u v)  ; u, v: stringhe
      (cond ((or (string=? u "") (string=? v ""))
             "")
            ((char=? (string-ref u 0) (string-ref v 0))
             (string-append (substring u 0 1)
                            (lcs (substring u 1) (substring v 1))))
            (else
             (longer (lcs (substring u 1) v) (lcs u (substring v 1))))
            )))
*/
  
// Traduzione in Java:
  
  public static String lcs( String u, String v ) {
  
    if ( u.equals("") || v.equals("") ) {
      return  "";
    } else if ( u.charAt(0) == v.charAt(0) ) {
      return  u.charAt(0) + lcs( u.substring(1), v.substring(1) );
    } else {
      return  longer( lcs(u.substring(1),v), lcs(u,v.substring(1)) );
    }
  }
  
  private static String longer( String u, String v ) {
  
    int m = u.length();
    int n = v.length();
    
    if ( m < n ) {
      return v;
    } else if ( m > n ) {
      return u;
    } else if ( Math.random() < 0.5 ) {  // scelta causale se m = n
      return v;
    } else {
      return u;
    }
  }
  
  
// Versione che applica la tecnica di memoization:
  
  public static String lcsMem( String u, String v ) {
  
    int m = u.length();
    int n = v.length();
    
    String[][] llcsDb = new String[ m+1 ][ n+1 ];
    
    for ( int i=0; i<=m; i=i+1 ) {
      for ( int j=0; j<=n; j=j+1 ) {
        llcsDb[i][j] = null;
    }}
    return lcsRec( u, v, llcsDb );
  }
  
  private static String lcsRec( String u, String v, String[][] llcsDb ) {
  
    int i = u.length();
    int j = v.length();
    
    if ( llcsDb[i][j] == null ) {
      if ( u.equals("") || v.equals("") ) {
        llcsDb[i][j] = "";
      } else if ( u.charAt(0) == v.charAt(0) ) {
        llcsDb[i][j] = u.charAt(0) + lcsRec( u.substring(1), v.substring(1), llcsDb );
      } else {
        llcsDb[i][j] = longer( lcsRec(u.substring(1),v,llcsDb),
                               lcsRec(u,v.substring(1),llcsDb) );
    }}
    return llcsDb[i][j];
  }
  
  
// Versione che applica la tecnica di programmazione dinamica:
// i, j rappresentano indici di suffissi di lunghezza m-i, n-j
  
  public static String lcsDp( String u, String v ) {
  
    int m = u.length();
    int n = v.length();
    
    // Sezione I: LLCS
    
    int[][] llcsDb = new int[ m+1 ][ n+1 ];
    
    for ( int j=n; j>=0; j=j-1 ) {
      llcsDb[m][j] = 0;
    }
    for ( int i=m; i>=1; i=i-1 ) {
      llcsDb[i][n] = 0;
    }

    for ( int i=m-1; i>=0; i=i-1 ) {
      for ( int j=n-1; j>=0; j=j-1 ) {
        if ( u.charAt(i) == v.charAt(j) ) {
          llcsDb[i][j] = 1 + llcsDb[i+1][j+1];
        } else {
          llcsDb[i][j] = Math.max( llcsDb[i+1][j], llcsDb[i][j+1] );
        }
    }}                // llcsDb[0][0] = LLCS(u,v)
    
    // Sezione II: LCS
    
    String lcs = "";  // Percorso attraverso la matrice llcsDb
    int i = 0;
    int j = 0;
    while ( llcsDb[i][j] > 0 ) {
      if ( u.charAt(i) == v.charAt(j) ) {
        lcs = lcs + u.charAt(i);
        i = i + 1;
        j = j + 1;
      } else if ( llcsDb[i+1][j] < llcsDb[i][j+1] ) {
        j = j + 1;
      } else if ( llcsDb[i+1][j] > llcsDb[i][j+1] ) {
        i = i + 1;
      } else if ( Math.random() < 0.5 ) {
        j = j + 1;
      } else {
        i = i + 1;
    }}

    for (int k = 0; k < m+1; k++) {
        for (int l = 0; l < n+1; l++) {
            System.out.print(llcsDb[k][l]+" ");
        }
        System.out.println("");
    }



    return lcs;       // lcs = LCS(u,v)
  }
  
  
// Soluzione alternativa che applica la tecnica di programmazione dinamica:
  
  public static String lcsDp2( String u, String v ) {
  
    int m = u.length();
    int n = v.length();
    
    String[] llcsDb = new String[ n+1 ];
    
    for ( int j=n; j>=0; j=j-1 ) {
      llcsDb[j] = "";
    }
    for ( int i=m-1; i>=0; i=i-1 ) {
      String w = "";
      for ( int j=n-1; j>=0; j=j-1 ) {
        if ( u.charAt(i) == v.charAt(j) ) {
          String t = u.charAt(i) + w;
          w = llcsDb[j];
          llcsDb[j] = t;
        } else {
          w = llcsDb[j];
          llcsDb[j] = longer( llcsDb[j], llcsDb[j+1] );
        }
    }}
    return llcsDb[0];
  }
  
  
  public static void main( String[] args ) {
  
    String u = "arto";
    String v = "parto";
    
    System.out.println( "Mem: lcs(" + u + "," + v + ") = " + lcsMem(u,v) + " : " + llcsMem(u,v) );
    System.out.println( "Dp:  lcs(" + u + "," + v + ") = " + lcsDp(u,v)  );
    System.out.println( "Dp2: lcs(" + u + "," + v + ") = " + lcsDp2(u,v) );
    System.out.println( "Rec: lcs(" + u + "," + v + ") = " + lcs(u,v)    + " : " + llcs(u,v)    );
  }
  
}  // class LCS


/* Esempi:

javac LCS.java

java LCS arto atrio
java LCS salva saliva
java LCS solido alito
java LCS stelvio trivio
java LCS capzioso cupido
java LCS cortigiana triglia
java LCS semplificazione affioramento
java LCS strabiliante abbigliamento
java LCS approvigionamento parametrico
*/

