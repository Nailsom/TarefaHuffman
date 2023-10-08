import java.util.PriorityQueue;
//Um PriorityQueue é usado quando os objetos devem ser processados com base na prioridade

public class Main {
    public static void main(String[] args) {
        String palavraTeste = "nailson onesio de almeida";

        int[] charFreq = new int[500];
        for(char c : palavraTeste.toCharArray())
            charFreq[c]++;

        ArvoreDeHuffman arvore = construirArvore(charFreq);

        System.out.println("----Tabela----");
        System.out.println("Caracter-Quantidade-Codigo");
        printCodigo(arvore, new StringBuffer());
        String codificado = codificado(arvore, palavraTeste);

        System.out.println("\nTexto codificado");
        System.out.println(codificado);

        System.out.println("\n\nTexto Decodificado");
        System.out.println(decodificado(arvore,codificado));
    }

    public static ArvoreDeHuffman construirArvore(int[] charFreq) {

        PriorityQueue<ArvoreDeHuffman> arvores = new PriorityQueue<ArvoreDeHuffman>();

            for (int i = 0; i < charFreq.length; i++) {
                if(charFreq[i] > 0)
                    arvores.offer(new FolhaDeHuffman(charFreq[i], (char)i));
            }

            while (arvores.size() > 1) {
                ArvoreDeHuffman a = arvores.poll();
                ArvoreDeHuffman b = arvores.poll();


                arvores.offer(new NoDeHuffman(a, b));
        }

            return arvores.poll();

    }


    public static String codificado(ArvoreDeHuffman arvore, String codificado){
        assert arvore != null;

        String textoCodificado = "";
        for (char c : codificado.toCharArray()){
            textoCodificado+=(obterCodigo(arvore, new StringBuffer(),c));
        }
        return  textoCodificado;
    }





    public static String decodificado(ArvoreDeHuffman arvore, String codificado) {
        assert arvore != null;
        String textoDecodificado = "";
        NoDeHuffman no = (NoDeHuffman) arvore;
        for (char codigo: codificado.toCharArray())
            if (codigo == '0'){
                if (no.esquerda instanceof FolhaDeHuffman) {
                textoDecodificado += ((FolhaDeHuffman)no.esquerda).valor;
                no = (NoDeHuffman)arvore;
                }else{
                no = (NoDeHuffman) no.esquerda;
                }
                }else if (codigo == '1'){
                if (no.direita instanceof FolhaDeHuffman) {
                    textoDecodificado += ((FolhaDeHuffman)no.direita).valor;
                    no = (NoDeHuffman)arvore;
                }else{
                    no = (NoDeHuffman) no.direita;
                }
            }
        return textoDecodificado;

        }




    public static void printCodigo(ArvoreDeHuffman arvore, StringBuffer prefixo) {
        assert arvore != null;

        if (arvore instanceof FolhaDeHuffman) {
            FolhaDeHuffman folha = (FolhaDeHuffman)arvore;

            System.out.println(folha.valor + "\t" + folha.frequencia + "\t\t" + prefixo);

        } else if (arvore instanceof NoDeHuffman) {
            NoDeHuffman no = (NoDeHuffman)arvore;

            prefixo.append('0');
            printCodigo(no.esquerda, prefixo);
            prefixo.deleteCharAt(prefixo.length()-1);

            prefixo.append('1');
            printCodigo(no.direita, prefixo);
            prefixo.deleteCharAt(prefixo.length()-1);
        }
    }


    public static String obterCodigo(ArvoreDeHuffman arvore, StringBuffer prefixo, char w) {
        assert arvore != null;

        if (arvore instanceof FolhaDeHuffman) {
            FolhaDeHuffman folha = (FolhaDeHuffman)arvore;

            if (folha.valor == w ){
                return prefixo.toString();
            }

        } else if (arvore instanceof NoDeHuffman) {
            NoDeHuffman no = (NoDeHuffman)arvore;


            prefixo.append('0');
            String left = obterCodigo(no.esquerda, prefixo, w);
            prefixo.deleteCharAt(prefixo.length()-1);

            prefixo.append('1');
            String right = obterCodigo(no.direita, prefixo,w);
            prefixo.deleteCharAt(prefixo.length()-1);

            if (left==null) return right; else return left;
        }
        return null;
    }





}

