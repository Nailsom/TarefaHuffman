//Esta interface impõe uma ordenação total aos objetos de cada classe que a implementa, o método 'compareTo' da classe é chamado de método de comparação natural.
abstract class ArvoreDeHuffman implements Comparable<ArvoreDeHuffman> {

    public final int frequencia;

    public ArvoreDeHuffman(int freq){
        frequencia = freq;
    }

    public int compareTo(ArvoreDeHuffman arvore){
        return frequencia - arvore.frequencia;
    }
}





