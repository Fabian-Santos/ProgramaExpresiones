package expresiones;
class Excepciones extends Exception{
    String errStr; 
    public Excepciones(String str) {
        errStr = str;
    }
    public String toString() {
        return errStr;
    }
}
class Comp {
    private int expi;
    private String token; 
    private int tipoToken;
    private String exp; 
    final int nada=0;
    final int sint=0;
    final int paren=1;
    final int deli=1;
    final int sinex=2;
    final int var=2;
    final int num=3;
    final int divcero=3;
    final String f="\0";
    public double evaluar(String cadena) throws Excepciones{
        double resul;
        exp=cadena;
        expi=0;
        obtieneToken();
        if(token.equals(f)){
            obtError(sinex); 
        } 
        resul= evaluar4();
        if(!token.equals(f)){ 
            obtError(sint);
        }
        return resul;
    }
    private double evaluar3() throws Excepciones{
            double resultado;
            if(token.equals("(")) {
                obtieneToken();
                resultado=evaluar4();
                if(!token.equals(")")){
                    obtError(paren);
                }
                obtieneToken();
            }else{
                resultado=valor();
            }
            return resultado;
        }
    private double evaluar4() throws Excepciones{
        char opc;
        double resul;
        double resulP;
        resul= evaluar5();
        while((opc=token.charAt(0))=='+'||opc=='-') {
            obtieneToken();
            resulP=evaluar5();
            switch(opc) {
                case '-':
                    resul=resul-resulP;
                break;
                case '+':
                    resul=resul+resulP;
                break;
            } 
        }
        return resul;
    }
    private double evaluar5() throws Excepciones{
        char op;
        double resultado;
        double resultadoParcial;
        resultado=evaluar6();
        while((op=token.charAt(0))=='*'||op== '/'||op =='%'){
            obtieneToken();
            resultadoParcial=evaluar6();
            switch(op) {
                case '*':
                resultado=resultado*resultadoParcial;
                break;
                case '/':
                    if(resultadoParcial==0.0){
                        obtError(divcero);
                    }
                    resultado=resultado/resultadoParcial;
                    break;
                    case '%':
                        if(resultadoParcial==0.0){
                            obtError(divcero);
                        }
                        resultado=resultado%resultadoParcial;
                    break;
                }
            }
            return resultado;
          }
        private double evaluar6() throws Excepciones{
            double resultado;
            double resultadoParcial;
            double ex;
            int t;
            resultado=evaluar7();
            if(token.equals("^")){
                obtieneToken();
                resultadoParcial = evaluar6();
                ex=resultado;
                if(resultadoParcial==0) {
                    resultado=1;
                }else{
                    for(t=(int)resultadoParcial-1;t>0;t--){
                        resultado = resultado * ex;
                    }
                }
            }
            return resultado;
        }
        private double evaluar7() throws Excepciones{
            double resultado;
            String  op;
            op="";
            if((tipoToken==deli)&&token.equals("+")||token.equals("-")){
                op=token;
                obtieneToken();
            }
            resultado=evaluar3();
            if(op.equals("-")){
                resultado=-resultado;
            }
            return resultado;
        }
        private double valor() throws Excepciones{
            double resultado = 0.0;
            switch(tipoToken){
                case num:
                    try {
                      resultado = Double.parseDouble(token);
                    } catch (NumberFormatException exc) {
                      obtError(sint);
                    }
                    obtieneToken();
                    break;
                default:
                    obtError(sint);
                    break;
            }
            return resultado;
        }
        private void obtError(int error) throws Excepciones{
            String[]err={"Error"};
            throw new Excepciones(err[error]);
        }
        private void obtieneToken(){
            tipoToken=nada;
            token="";
            if(expi==exp.length()){
                token=f;
                return;
            }
            while(expi<exp.length()&&Character.isWhitespace(exp.charAt(expi))){
                ++expi;
            }
            if(expi==exp.length()){
                token=f;
                return; 
            }
            if(delimitador(exp.charAt(expi))){
                token+=exp.charAt(expi);
                expi++;
                tipoToken=deli;
            }else if(Character.isLetter(exp.charAt(expi))){ 
                while(!delimitador(exp.charAt(expi))){
                    token+=exp.charAt(expi);
                    expi++;
                    if(expi>=exp.length()){
                        break;
                    }
                }
            tipoToken=var;
            }else if(Character.isDigit(exp.charAt(expi))){ 
                while(!delimitador(exp.charAt(expi))){
                    token +=exp.charAt(expi);
                    expi++;
                    if(expi>=exp.length()){
                        break;
                    }
                }
            tipoToken=num;
            }else{ 
                token=f;
                return;
            }
        }
        private boolean delimitador(char c){
            if (("+-/*^=%()".indexOf(c)!= -1)){
                return true;
            }else{
                return false;
            }
          }
        }
