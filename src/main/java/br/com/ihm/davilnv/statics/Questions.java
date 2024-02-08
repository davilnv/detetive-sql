package br.com.ihm.davilnv.statics;

import br.com.ihm.davilnv.model.Question;

import java.util.List;

public class Questions {

    public static List<Question> getQuestions() {
        return List.of(
                new Question("Quem acessou o banco de dados do museu nas últimas 24 horas?"),
                new Question("Houve algum acesso não autorizado às câmeras de segurança que poderia indicar um possível cúmplice ou suspeito?"),
                new Question("Quais funcionários do museu têm acesso para liberar a caixa de vidro onde o diamante estava armazenado?"),
                new Question("Há registros de atividade suspeita nos registros de transações nas últimas semanas?"),
                new Question("Existem registros de operações de exclusão no banco de dados relacionadas ao diamante desaparecido?")
        );
    }

}
