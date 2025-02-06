package org.example;
import java.time.LocalDate;
import java.util.*;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class Main {

public static final String ANSI_RESET = "\u001B[0m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {


        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 12, 5), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 2, 5), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 2, 9), new BigDecimal("2799.93"), "Gerente"));




        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));


        System.out.println(ANSI_BLUE+"Funcionarios:"+ANSI_RESET);
        for (Funcionario funcionario : funcionarios) {
            System.out.println(ANSI_PURPLE+"*"+ANSI_RESET+funcionario);
        }


        for (Funcionario funcionario : funcionarios) {
            BigDecimal aumento = funcionario.getSalario().multiply(BigDecimal.valueOf(0.10));
            funcionario.setSalario(funcionario.getSalario().add(aumento));
        }


        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));


        System.out.println(ANSI_BLUE+"\nFuncionarios agrupados por funcao:"+ANSI_RESET);
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println(ANSI_PURPLE+entry.getKey()+ANSI_RESET + " -> "+funcionario);
            }
        }


        System.out.println(ANSI_BLUE+"\nFuncionarios com aniversrio entre os meses 10 e 12:"+ANSI_RESET);
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println(ANSI_PURPLE+"*"+ANSI_RESET+funcionario);
            }
        }


        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getIdade))
                .orElseThrow();

        System.out.println(ANSI_BLUE+"\nFuncionario com a maior idade : \n"+ANSI_RESET + ANSI_PURPLE+funcionarioMaisVelho.getNome()+ANSI_RESET + " -> Idade: " + funcionarioMaisVelho.getIdade());


        System.out.println(ANSI_BLUE+"\nFuncionarios em ordem alfabetica:"+ANSI_RESET);
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(soutFuncionario -> System.out.println(ANSI_PURPLE+"*"+ANSI_RESET+soutFuncionario));


        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(ANSI_BLUE+"\nTotal dos salarios:\n"+ANSI_RESET+ANSI_PURPLE+"=> "+ANSI_RESET+" R$ " + String.format("%,.2f", totalSalarios).replace(",", ".").replace(".", ","));


        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println(ANSI_BLUE+"\nQuantos salarios minimos cada funcionário recebe:"+ANSI_RESET);
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(ANSI_PURPLE+"*"+ANSI_RESET+funcionario.getNome() + " recebe -> " + salariosMinimos + " salários mínimos.");
        }
    }
}