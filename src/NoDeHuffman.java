class NoDeHuffman extends ArvoreDeHuffman {

    public final ArvoreDeHuffman esquerda,direita;
        public NoDeHuffman(ArvoreDeHuffman e,ArvoreDeHuffman d){
            super(e.frequencia + d.frequencia);
            esquerda = e;
            direita = d;
        }

}