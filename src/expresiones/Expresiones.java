package expresiones;
import java.util.Scanner;

public class Expresiones {
    public static void main(String[] args) {
        Scanner leer=new Scanner(System.in);
        boolean cont=true;
        int opc;
        int str=0,cara=0,nu=0;
        String n="",st="",c="";
        while(cont){
            System.out.println("*** ELIGUE ALGUNA OPCION ***");
            System.out.println("1. Expresion aritmetica");
            System.out.println("2. Expresion logica");
            System.out.println("3. Expresion logica V2");
            System.out.println("4. Salir");
            opc=leer.nextInt();
            switch(opc){
                case 1:
                    new Arit();
                    break;   
                case 2:
                    System.out.println("Expresion para solo resolver: [(p->q)^p]->q");
                    String p[]={"v","v","f","f"},q[]={"v","f","v","f"};
                    String a1[]=new String[4];
                    for (int i = 0; i < a1.length; i++) {
                        if (p[i].equals("v")&&q[i].equals("f")) {
                            a1[i]="f";
                        }else{
                            a1[i]="v";
                        }
                        System.out.println("Resul aux: "+a1[i]);
                    }
                        for (int i = 0; i < a1.length; i++) {
                            if (a1[i].equals("v")&&p[i].equals("v")) {
                            a1[i]="v";
                        }else{
                            a1[i]="f";
                        }
                        System.out.println("Resul aux2: "+a1[i]);
                        }
                        for (int i = 0; i < a1.length; i++) {
                        if (a1[i].equals("v")&&p[i].equals("f")) {
                            a1[i]="f";
                        }else{
                            a1[i]="v";
                        }
                        System.out.println("Resultado Final: "+a1[i]);
                    }
                    break;
                    
                case 3:
                    String ora="(kl 9 + 8.6 ) * .1 / .5^2 - 76 a";
                    String a[]=ora.split(" ");
                    for (int i=0; i<a.length; i++){
                       if(a[i].matches("[a-zA-Z|[^0-9.]]")) {
                            cara++;
                            c=c+a[i]+" ";
                       }else if(a[i].matches("[0-9.]+")){
                            nu++;
                            n=n+a[i]+" ";
                        }else{
                           str++;        
                           st=st+a[i]+" ";
                       }
                    }
                    System.out.println("La cantidad de Caracteres Son: "+cara+" Y los Caracteres son: "+c);
                    System.out.println("La cantidad de Numeros es: "+nu+" Y los Numeros son: "+n);
                    System.out.println("La cantidad de Texto es: "+str+" Y los Textos son: "+st);
                    break;
                case 4:
                    cont=false;
                    break;
                default:
                    System.out.println("Ingresaste valor fuera de rango");
                    break;
            }
        }
    }
    
}
