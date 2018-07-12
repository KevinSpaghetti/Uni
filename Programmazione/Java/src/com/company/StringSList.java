package com.company;

public class StringSList {                    
    
    public static final StringSList NULL_INTLIST = new StringSList();

    private final boolean empty;             
    private final String first;                
    private final StringSList rest;

    public StringSList() {                      
        
        empty = true;
        first = "";                             
        rest = null;
    }

    public StringSList(String e, StringSList il ) {  
        
        empty = false;
        first = e;
        rest = il;
    }



    public boolean isNull() {                
        
        return ( empty );
    }


    public String car() {                       
        
        return first;                          
    }


    public StringSList cdr() {                  
        
        return rest;                           
    }

    public StringSList cons(String e ) {          
        
        return new StringSList( e, this );
    }

    public int length() {                    

        return isNull() ? 0 : 1+cdr().length();

        /*
        if ( isNull() ) {
            return 0;
        } else {
            return ( 1 + cdr().length() );
        }
        */
    }


    public String listRef(int k ) {            

        return k==0 ? car() : cdr().listRef(k-1);

        /*
        if ( k == 0 ) {
            return car();
        } else {
            return ( cdr().listRef(k-1) );
        }
        */
    }


    public boolean equals( StringSList il ) {   
        
        if ( isNull() || il.isNull() ) {
            return ( isNull() && il.isNull() );
        } else if ( car() == il.car() ) {
            return cdr().equals( il.cdr() );
        } else {
            return false;
        }
    }


    public StringSList append(StringSList il ) {  
        
        if ( isNull() ) {
            return il;
        } else {
            
            return ( cdr().append(il) ).cons( car() );
        }
    }


    public StringSList reverse() {              
        
        return reverseRec( new StringSList() );
    }

    private StringSList reverseRec(StringSList re ) {

        if ( isNull() ) {                      
            return re;
        } else {
            
            return cdr().reverseRec( re.cons(car()) );
        }
    }


    

    public String toString() {               
        
        if ( empty ) {
            return "()";
        } else if ( rest.isNull() ) {
            return "(" + first + ")";
        } else {
            String rep = "(" + first;
            StringSList r = rest;
            while ( !r.isNull() ) {
                rep = rep + ", " + r.car();
                r = r.cdr();
            }
            return ( rep + ")" );
        }
    }


}  

