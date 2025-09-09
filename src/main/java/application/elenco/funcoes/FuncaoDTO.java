package application.elenco.funcoes;

public record FuncaoDTO(Long id, String descricao) {
    public FuncaoDTO(Funcao dados) {
        this(dados.getId(), dados.getDescricao());
    }
}
