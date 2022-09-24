package br.unigran.controledeabastecimento;

public class Registro {
    private String KMatual;//nome
    private String QNTAbastecida;//telefone
    private String DIAAbastecido;//data
    private String VALORAbastecido;//novo
    private Integer id;

    public String getKMatual() {return KMatual; }

    public void setKMatual(String KMatual) { this.KMatual = KMatual; }

    public String getQNTAbastecida() {return QNTAbastecida; }

    public void setQNTAbastecida(String QNTAbastecida) { this.QNTAbastecida = QNTAbastecida; }

    public String getDIAAbastecido() {return DIAAbastecido; }

    public void setDIAAbastecido(String DIAAbastecido) { this.DIAAbastecido = DIAAbastecido; }

    public String getVALORAbastecido() {return VALORAbastecido; }

    public void setVALORAbastecido(String VALORAbastecido) { this.VALORAbastecido = VALORAbastecido; }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    @Override
    public String toString() {
        return
                "Km - " + KMatual +
                        " | Qnt - " + QNTAbastecida +
                        " | Dia - " + DIAAbastecido +
                        " | Valor - " + VALORAbastecido;
    }
}
