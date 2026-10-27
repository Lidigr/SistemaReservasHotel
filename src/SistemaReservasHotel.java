import java.text.NumberFormat;
import java.util.Scanner;

public class SistemaReservasHotel {

        private static final Scanner scanner = new Scanner(System.in);
        private static final Reserva[] reservas = new Reserva[10];
        private static int quantidade = 0;

        public static void main(String[] args) {

            int opcao;

            do {
                System.out.println("\n===== SISTEMA DE RESERVAS =====");
                System.out.println("1 - Nova reserva");
                System.out.println("2 - Listar reservas");
                System.out.println("3 - Buscar reserva por nome");
                System.out.println("4 - Ordenar reservas por dias");
                System.out.println("5 - Sair");
                System.out.print("Escolha uma opção: ");

                opcao = lerInteiro();

                switch (opcao) {
                    case 1:
                        novaReserva();
                        break;

                    case 2:
                        listarReservas();
                        break;

                    case 3:
                        buscarReserva();
                        break;

                    case 4:
                        ordenarReservas();
                        break;

                    case 5:
                        System.out.println("Sistema encerrado!");
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }

            } while (opcao != 5);

            scanner.close();
        }

        // Cadastra uma nova reserva
        public static void novaReserva() {

            if (quantidade >= reservas.length) {
                System.out.println("Limite de 10 reservas atingido!");
                return;
            }

            System.out.print("Nome do hóspede: ");
            String nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("O nome não pode ficar vazio.");
                return;
            }

            System.out.print("Tipo do quarto: ");
            String tipoQuarto = scanner.nextLine().trim();

            if (tipoQuarto.isEmpty()) {
                System.out.println("O tipo do quarto não pode ficar vazio.");
                return;
            }

            System.out.print("Número de dias: ");
            int dias = lerInteiro();

            if (dias < 1) {
                System.out.println("Os dias devem ser pelo menos 1.");
                return;
            }

            System.out.print("Valor da diária: R$ ");
            double diaria = lerDecimal();

            if (diaria <= 0 || Double.isNaN(diaria)
                    || Double.isInfinite(diaria)) {
                System.out.println("A diária deve ser maior que zero.");
                return;
            }

            reservas[quantidade] =
                    new Reserva(nome, tipoQuarto, dias, diaria);

            quantidade++;

            System.out.println("Reserva cadastrada com sucesso!");
        }

        // Lista todas as reservas cadastradas
        public static void listarReservas() {

            if (quantidade == 0) {
                System.out.println("Nenhuma reserva cadastrada.");
                return;
            }

            System.out.println("\n===== RESERVAS CADASTRADAS =====");

            for (int i = 0; i < quantidade; i++) {
                System.out.println("\nReserva " + (i + 1));
                System.out.println(reservas[i].toString());
                System.out.println("------------------------------");
            }
        }

        // Busca reservas por parte do nome, ignorando maiúsculas
        public static void buscarReserva() {

            if (quantidade == 0) {
                System.out.println("Nenhuma reserva cadastrada.");
                return;
            }

            System.out.print("Digite o nome ou parte do nome: ");
            String nomeBusca = scanner.nextLine().trim();

            if (nomeBusca.isEmpty()) {
                System.out.println("Digite um nome para pesquisar.");
                return;
            }

            boolean encontrou = false;

            for (int i = 0; i < quantidade; i++) {

                if (reservas[i].getNomeHospede()
                        .toLowerCase()
                        .contains(nomeBusca.toLowerCase())) {

                    System.out.println("\nReserva encontrada:");
                    System.out.println(reservas[i]);
                    System.out.println("------------------------------");
                    encontrou = true;
                }
            }

            if (!encontrou) {
                System.out.println("Nenhuma reserva encontrada.");
            }
        }

        // Ordena as reservas do maior para o menor número de dias
        public static void ordenarReservas() {

            if (quantidade == 0) {
                System.out.println("Nenhuma reserva para ordenar.");
                return;
            }

            for (int i = 0; i < quantidade - 1; i++) {

                for (int j = 0; j < quantidade - 1 - i; j++) {

                    if (reservas[j].getNumeroDias()
                            < reservas[j + 1].getNumeroDias()) {

                        Reserva auxiliar = reservas[j];
                        reservas[j] = reservas[j + 1];
                        reservas[j + 1] = auxiliar;
                    }
                }
            }

            System.out.println("Reservas ordenadas por dias!");
            listarReservas();
        }

        // Lê números inteiros e trata entradas inválidas
        public static int lerInteiro() {

            while (true) {
                try {
                    int numero = Integer.parseInt(scanner.nextLine().trim());
                    return numero;
                } catch (NumberFormatException e) {
                    System.out.print("Digite um número inteiro válido: ");
                }
            }
        }

        // Lê valores decimais aceitando vírgula ou ponto
        public static double lerDecimal() {

            while (true) {
                try {
                    String entrada = scanner.nextLine()
                            .trim()
                            .replace(",", ".");

                    return Double.parseDouble(entrada);
                } catch (NumberFormatException e) {
                    System.out.print("Digite um valor válido: R$ ");
                }
            }
        }
    }
