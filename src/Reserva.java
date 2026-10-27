public class Reserva {

        private String nomeHospede;
        private String tipoQuarto;
        private int numeroDias;
        private double valorDiaria;

        // Construtor completo
        public Reserva(String nomeHospede, String tipoQuarto,
                       int numeroDias, double valorDiaria) {
            this.nomeHospede = nomeHospede;
            this.tipoQuarto = tipoQuarto;
            setNumeroDias(numeroDias);
            setValorDiaria(valorDiaria);
        }

        // Getters
        public String getNomeHospede() {
            return nomeHospede;
        }

        public String getTipoQuarto() {
            return tipoQuarto;
        }

        public int getNumeroDias() {
            return numeroDias;
        }

        public double getValorDiaria() {
            return valorDiaria;
        }

        // Setters
        public void setNomeHospede(String nomeHospede) {
            this.nomeHospede = nomeHospede;
        }

        public void setTipoQuarto(String tipoQuarto) {
            this.tipoQuarto = tipoQuarto;
        }

        public void setNumeroDias(int numeroDias) {
            if (numeroDias < 1) {
                throw new IllegalArgumentException(
                        "O número de dias deve ser pelo menos 1."
                );
            }
            this.numeroDias = numeroDias;
        }

        public void setValorDiaria(double valorDiaria) {
            if (valorDiaria <= 0 || Double.isNaN(valorDiaria)
                    || Double.isInfinite(valorDiaria)) {
                throw new IllegalArgumentException(
                        "O valor da diária deve ser maior que zero."
                );
            }
            this.valorDiaria = valorDiaria;
        }

        // Calcula o valor total da hospedagem
        public double calcularValorTotal() {
            return numeroDias * valorDiaria;
        }

        // Exibe os dados da reserva
        @Override
        public String toString() {
            return "Hóspede: " + nomeHospede
                    + "\nTipo do quarto: " + tipoQuarto
                    + "\nDias de estadia: " + numeroDias
                    + "\nValor da diária: R$ "
                    + String.format("%.2f", valorDiaria)
                    + "\nValor total: R$ "
                    + String.format("%.2f", calcularValorTotal());
        }
    }

