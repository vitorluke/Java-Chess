
import game.chess_board;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        chess_board board = new chess_board();

        try {
            board.init_board();

            System.out.println("=== XADREZ ===");
            System.out.println("Digite a origem e o destino.");
            System.out.println("Exemplo: e2 e4");
            System.out.println("Digite 'sair' para encerrar.");

            while (!board.is_game_over()) {

                board.print_board();

                System.out.println(board.get_game_result());

                System.out.print("Movimento: ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("sair")) {
                    break;
                }

                try {
                    String[] positions = input.split("\\s+");

                    if (positions.length != 2) {
                        throw new IllegalArgumentException(
                            "Digite duas posições, por exemplo: e2 e4"
                        );
                    }

                    int c1 = positions[0].toLowerCase().charAt(0) - 'a';
                    int r1 = 8 - Integer.parseInt(
                        positions[0].substring(1)
                    );

                    int c2 = positions[1].toLowerCase().charAt(0) - 'a';
                    int r2 = 8 - Integer.parseInt(
                        positions[1].substring(1)
                    );

                    if (!validPosition(positions[0])
                            || !validPosition(positions[1])) {
                        throw new IllegalArgumentException(
                            "Posição inválida. Use a1 até h8."
                        );
                    }

                    if (!board.move_piece(r1, c1, r2, c2)) {
                        System.out.println(
                            "Movimento inválido! Tente novamente."
                        );
                    }

                } catch (NumberFormatException e) {
                    System.out.println(
                        "Erro: informe posições como e2 e e4."
                    );
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println(
                        "Erro ao processar movimento: " + e.getMessage()
                    );
                }
            }

            System.out.println("Partida encerrada.");

        } catch (Exception e) {
            System.out.println(
                "Erro ao iniciar o jogo: " + e.getMessage()
            );
        } finally {
            scanner.close();
        }
    }

    private static boolean validPosition(String position) {
        return position.matches("[a-hA-H][1-8]");
    }
}
