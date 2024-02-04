package br.com.ihm.davilnv.statics;

import br.com.ihm.davilnv.model.Dialog;

public class SceneInfo {

    public static final Dialog[] dialoguesNewspaper = {
            new Dialog("Um misterioso sumiço de um diamante do Museu das Relíquias Reais Britânicas foi registrado hoje, deixando os curadores e historiadores perplexos. O diamante, conhecido como 'O Olho de Windsor', é uma das joias mais valiosas e icônicas da coleção real e tem uma rica história que remonta a séculos.", Character.NEWSCASTER),
            new Dialog("O diamante, uma gema deslumbrante de lapidação impecável, era originalmente parte da coroa real usada por diversos monarcas britânicos em importantes cerimônias e ocasiões especiais. Sua história é repleta de lendas e mistérios, e muitos acreditam que ele esteja ligado a eventos históricos importantes da realeza britânica.", Character.NEWSCASTER),
            new Dialog("As autoridades estão investigando o desaparecimento do diamante, que foi detectado durante uma verificação de rotina da integridade dos dados esta manhã. Os detalhes sobre como o diamante foi \"removido\" do museu permanecem envoltos em mistério, para isto estão sendo feitas algumas operações examinando registros de transações e entrevistando funcionários do museu em busca de pistas.", Character.NEWSCASTER),
            new Dialog("Enquanto as investigações estão em andamento, o diretor do museu, Sir Alexander Kensington, expressou sua profunda preocupação com o desaparecimento do diamante e destacou a importância de sua recuperação.", Character.NEWSCASTER),
            new Dialog("O 'Olho de Windsor' é uma peça insubstituível de nossa nossa herança real e um símbolo da história britânica. Faremos todo o possível para garantir sua volta segura ao museu\", disse ele em uma coletiva de imprensa. Além disto convocou diversos especialístas para que possa recuperar esta joia o mais rápido possível. \"Estamos oferecendo uma recompensa para quem puder nos ajudar, é importante o conhecimento em banco de dados e em linguagem SQL, pois nosso sistema de segurança possui diversas rotinas que registram as operações realizadas no museu, sua ajuda é de grande importância.", Character.DIRECTOR),
            new Dialog("A notícia do desaparecimento do diamante está gerando grande comoção na comunidade de programadores SQL, com muitos ansiosos para testar suas habilidades e ajudar a resolver este enigma. A nação inteira aguarda ansiosamente os desenvolvimentos desta intrigante história de desaparecimento e esperando que a joia preciosa seja recuperada para ser apreciada pelas gerações futuras.", Character.NEWSCASTER)
    };

    public static final Dialog[] dialoguesItEmployee = {
            new Dialog("Olá, sou Sophie Campbell, sou a responsável pelo departamento de TI, ou também conhecida como a moça que arruma o computador hehe, sou maga da tecnologia.", Character.IT_EMPLOYEE),
            new Dialog("Mas não me pergunte nada sobre história haha, não faço a mínima idéia do que é esse olho de não sei quem. Tudo que eu preciso saber está neste computador aqui ao lado, ele possui todos os registros de segurança do museu...", Character.IT_EMPLOYEE),
            new Dialog("Usuários, regras e protocolos e até registros captados pelas camêras e pelos sensores. Como foi anunciado, se existe alguma pista com certeza está registrado em nosso sistema.", Character.IT_EMPLOYEE),
            new Dialog("Vamos ver se a pessoa foi inteligente o suficiente para não deixar rastros. Eu não consegui encontrar nada, mas tenho certeza que você é capaz de solucionar isto.", Character.IT_EMPLOYEE),
            new Dialog("Para você acessar o baco de dados, vai precisar de um usuário e senha, então criei um acesso para você, login 'detetive' e senha '123'.", Character.IT_EMPLOYEE),
            new Dialog("Estes acessos são temporários, então cuidado para não levar uma eternidade para resolver o caso haha. Boa sorte!", Character.IT_EMPLOYEE)
    };

    public static final Dialog[] dialoguesDirector = {
            new Dialog("Primeiramente me chame de Sir. Sir Alexander Kensington, sou o diretor deste museu e um grande admirador da Casa de Windsor.", Character.DIRECTOR),
            new Dialog("Eu estou muito preocupado com o sumiço deste diamante, pois as pessoas não sabem, mas esta peça tem um grande valor, não só financeiro, mas em textos antigos é descrito que possui um poder especial para aquele que o possui.", Character.DIRECTOR),
            new Dialog("Sempre quis pegar na peça para olhar de perto e entender os seus mistérios.", Character.DIRECTOR),
            new Dialog("Mas isto não é permitido. Enfim, espero que você resolva este mistério e possa nos trazer o culpado, para que nosso lindo diamante volte a ser exposto aqui.", Character.DIRECTOR),
            new Dialog("É importante que você compareça a sala administrativa do TI e converse com Sophie Campbell.", Character.DIRECTOR),
            new Dialog("Ela é responsável pelo departamento e gerencia o sistema de banco de dados, com toda certeza ela poderá te ajudar.", Character.DIRECTOR)
    };

    public static final Dialog[] dialoguesChiefCurator = {
            new Dialog("Oi meu querido, eu não tenho muito tempo, então vou ser breve, isto aqui está uma loucura... ", Character.CHIEF_CURATOR),
            new Dialog("A que falta de educação a minha, me chamo Eleanor Thornton, sou doutora em história, então pode me chamar de Dra. Eleanor, por favor.", Character.CHIEF_CURATOR),
            new Dialog("Conheço este museu como a palma da minha mão, até porque eu que organizei todas as exposições. Estou totalmente desesperada, não sei como isto foi acontecer, com todos essses protocolos de segurança, regras e registros no banco de dados.", Character.CHIEF_CURATOR),
            new Dialog("Quem sumiu com o Olho de Windsor é um conhecedor da história e deste museu, deve saber tudo sobre a peça e principalmente como poderia fazer isto.", Character.CHIEF_CURATOR),
            new Dialog("O diamante é uma joia incrível, foi encontrada no século X, pelo tão conhecido Athelstan, considerado por muitos como primeiro rei da inglaterra, desta época em diante, não se sabe ao certo por onde esta relíquia deve ter passado.", Character.CHIEF_CURATOR),
            new Dialog("O intrigante é que ao ser visto a frente de uma forte luz, podemos ver um olho dentro do diamante. Não sabemos também como ele foi parar como adorno nas coroas reais.", Character.CHIEF_CURATOR),
            new Dialog("Mas sabemos que este nome foi escolhido pelo rei George V, primeiro monarca da Casa de Windsor, claro ficou por diversos anos como sendo um destaque da coroa.", Character.CHIEF_CURATOR),
            new Dialog("Posteriormente foi entregue ao nosso museu. Muitos conhecem da história, porém só uma pessoa poderá te dar mais detalhes.", Character.CHIEF_CURATOR),
            new Dialog("Sir Alexander Kensington o diretor deste museu, você encontra ele na sala da diretoria à esquerda do museu.", Character.CHIEF_CURATOR)
    };

    public static final Dialog[] dialoguesSecurityGuard = {
            new Dialog("Olá, Sou o William Smith, segurança do museu, não, não sou o famoso Will Smith.", Character.SECURITY_GUARD),
            new Dialog("Sou apenas um segurança que trabalha aqui entre a noite e madrugada e tenho que ficar olhando essas reliíquias milionárias, mas ganhando praticamente nada... Enfim, acho que você está aqui para saber algo sobre o famoso diamante... ", Character.SECURITY_GUARD),
            new Dialog("Então eu vi poucas movimentações aqui nesses últimos dias, poucos visitantes e não tivemos nenhum problema para ficar em alerta.", Character.SECURITY_GUARD),
            new Dialog("Mas tem uma pessoa que achei estranho, ela vem visitar o diamante todos os dias, passa horas olhando e depois vai embora, talvez te ajude em algo.", Character.SECURITY_GUARD)
    };

    public static final Dialog[] dialoguesCaretaker = {
            new Dialog("Opa, sou James Turner, sou o zelador do museu, cuido da limpeza e manutenção, mas vou logo avisando a você, eu não tenho nada haver com isso!", Character.CARETAKER),
            new Dialog("Acho aquele diamante feio e sinto medo daquela coisa horrorosa. Como que aquilo pode ser tão valioso? Só sei porque dei um Google hehe. ", Character.CARETAKER),
            new Dialog("Enfim, para que fique claro, sim, eu tenho acesso a todas as áreas daqui, mas isto é óbvio, quem vai entrar nos dutos para fazer limpeza e tirar o mofo? Só o garotão aqui e não recebo mais nada por isto! ", Character.CARETAKER),
            new Dialog("Eu estava aqui no dia do desaparecimento do diamante, mas não vi nada de estranho, estava limpando o chão e não vi ninguém suspeito. ", Character.CARETAKER),
            new Dialog("O máximo que vi foi a curadora chefe meio preocupada, andando entre as salas, deve ter visto algo, eu falaria com ela, talvez possa ajudar. Ela é loira e sempre fica em uma das salas a direita do museu.", Character.CARETAKER)
    };

    public static final Dialog[] dialoguesMuseumVisitor = {
            new Dialog("Oi, eu sou a Lucy Bennett, venho aqui todos os dias para admirar a coisa mais bela que pode ser criada, não sei se você conseguiu ver alguma vez o Olho de Windsor, mas deveria ser uma das 7 maravilhas do mundo!", Character.MUSEUM_VISITOR),
            new Dialog("Eu amo tanto este museu que um dia solicitei para entrar nas áreas administrativas, o diretor já me considera até um ponto de referência aqui haha, demorei para ganhar confiança, então ele autorizou e fiquei super feliz.", Character.MUSEUM_VISITOR),
            new Dialog("Eu estava aqui no dia do desaparecimento do diamante, fiquei olhando as relíquias e vi um homem estranho, ele estava com um casaco preto e uma touca, ficou olhando o diamante por um tempo e depois saiu.", Character.MUSEUM_VISITOR),
            new Dialog("Eu não sei se ele é um dos funcionários do museu, já vi ele outras vezes, mas acho bem suspeito. Seria bom investigar.", Character.MUSEUM_VISITOR)
    };
    public static final Dialog[] dialoguesNewscaster = {
            new Dialog("Olá, sou Isabella Kensington, sou apenas jornalista que está acompanhando o caso.  E antes que me pergunte, sim sou irmã do diretor deste museu, o Alexander.", Character.NEWSCASTER),
            new Dialog("Ele vive encantado por este diamante e provavelmente vai te contar alguma ficção sobre haha não sei de onde ele inventa estas coisas.", Character.NEWSCASTER),
            new Dialog("Bem você pode entrevistar as pessoas que estão neste museu, aparentemente todos são suspeitos e podem ser acusados.", Character.NEWSCASTER),
            new Dialog("Realize perguntas, encontre pistas e assim que tiver um veredito, basta vir até este painel de informações aqui na entrada do museu e informar quem sumiu com o diamante. Boa sorte!", Character.NEWSCASTER),
    };

}
