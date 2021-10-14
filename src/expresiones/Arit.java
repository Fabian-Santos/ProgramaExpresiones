package expresiones;
import java.util.Scanner;
import java.util.Stack;
public class Arit {
    public Arit(){
        Scanner leer=new Scanner(System.in);
        System.out.println("Ingrese la expresión: ");
        String exp=leer.nextLine();
        String expresion=depurar(exp);
        System.out.println("Expresion"+expresion);
        System.out.println("Su expresion ingresada es: "+exp); 
        Comp analizador= new Comp();  
        String[] arrayInfix=expresion.split(" ");
        Stack < String > Ex=new Stack < String > ();
        Stack < String > P= new Stack < String > ();
        Stack < String > S= new Stack < String > ();
        for (int i = arrayInfix.length-1; i >= 0; i--) {
            Ex.push(arrayInfix[i]);
        }
            while(!Ex.isEmpty()){
                switch (prefijo(Ex.peek())){
                    case 1:
                        P.push(Ex.pop());
                        break;
                    case 3:
                    case 4:
                        while(prefijo(P.peek())>=prefijo(Ex.peek())){
                            S.push(P.pop());
                        }
                        P.push(Ex.pop());
                        break;
                    case 2:
                        while(!P.peek().equals("(")){
                            S.push(P.pop());
                        }
                        P.pop();
                        Ex.pop();
                        break;
                    default:
                        S.push(Ex.pop());
                }
            }
        String infi=expresion.replace(" ", "");
        String postfi=S.toString().replaceAll("[\\]\\[,]", "");  
    try {
            System.out.println("El Resultado de la expresion ingresada es: "+analizador.evaluar(exp));
            System.out.println();
        }catch(Excepciones exc) {
        }    
    }
    private static String depurar(String st){
        st=st.replaceAll("\\s+", "");
        st="("+st+")";
        String sim= "+-*/()";
        String str= "";    
        for (int i = 0; i < st.length(); i++) {
            if(sim.contains("" + st.charAt(i))){
                str+=" "+ st.charAt(i)+ " ";
            }else str+=st.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
    }
    private static int prefijo(String op){
        int prf=1;
        if(op.equals("^")) prf=5;
        if(op.equals("*") || op.equals("/")) prf=4;
        if(op.equals("+") || op.equals("-")) prf=3;
        if(op.equals(")")) prf=2;
        if(op.equals("(")) prf=1;
        return prf;  
    }
}
