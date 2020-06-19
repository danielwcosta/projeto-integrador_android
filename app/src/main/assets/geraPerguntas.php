<?php

$url = "timesfiltrados.json";

$arquivo = file_get_contents($url);
$arrayArquivo = json_decode($arquivo);
$arrayTimes = $arrayArquivo->times;


function geraPerguntasRespostas($tipo,$resposta,$pergunta1,$pergunta2,$imagem){
    global $arrayTimes;
    foreach ($arrayTimes as $key => $time) {
                
        $arrayRespostas[$key] = array(
            $time->$resposta,
            $time->strCountry,
        );
    }

    foreach ($arrayRespostas as $key => $value) {
  
        for ($i=0; $i < 1;) { 
            $alternativa1 = $arrayRespostas[mt_rand(0,count($arrayRespostas)-1)];
            $alternativa2 = $arrayRespostas[mt_rand(0,count($arrayRespostas)-1)];
            $alternativa3 = $arrayRespostas[mt_rand(0,count($arrayRespostas)-1)];
            
            $validaPais1 = ($alternativa1[1] == $arrayRespostas[$key][1])?true:false;
            $validaPais2 = ($alternativa2[1] == $arrayRespostas[$key][1])?true:false;
            $validaPais3 = ($alternativa3[1] == $arrayRespostas[$key][1])?true:false;
            
            
            if       ($tipo == "strTeam" &&  $validaPais1){ $i=0;
            }else if ($tipo == "strTeam" &&  $validaPais2){ $i=0;
            }else if ($tipo == "strTeam" &&  $validaPais3){ $i=0;
            }else if (
                   $alternativa1[0] != $arrayRespostas[$key][0]
                && $alternativa2[0] != $arrayRespostas[$key][0]
                && $alternativa3[0] != $arrayRespostas[$key][0]
                && $alternativa1[0] != $alternativa2[0]
                && $alternativa1[0] != $alternativa3[0]
                && $alternativa2[0] != $alternativa3[0]) {
                                        
                    $arrayAlternativas[$key] =array(
                    $arrayRespostas[$key][0],
                    $alternativa1[0],
                    $alternativa2[0],
                    $alternativa3[0]
                );
                shuffle($arrayAlternativas[$key]);
                $i++;                
            } 
        }
    }

    foreach ($arrayTimes as $key => $time) {
        
        if ( ($tipo == "strStadiumThumb"  
            ||$tipo == "strTeamJersey")
            && (empty($arrayTimes[$key]->$tipo))) {
            //Não cria pergunta
        }else{
            $arrayPerguntas[$key] = array(
                'id_tipo'           => $key + 1,
                'tipo'              => $tipo,
                'imagem'            => $time->$imagem,
                'pergunta'          => $pergunta1 . $time->$pergunta2 . "?",
                'alternativa1'      => $arrayAlternativas[$key][0],
                'alternativa2'      => $arrayAlternativas[$key][1],
                'alternativa3'      => $arrayAlternativas[$key][2],
                'alternativa4'      => $arrayAlternativas[$key][3],
                'resposta'          => $time->$resposta,
            ); 
        }
        
    }
    return $arrayPerguntas;
}

$tipo = array("strCountry","intStadiumCapacity","strStadiumLocation","strStadium", "strStadiumThumb", "strTeamBadge","strTeamJersey","strLeague","intFormedYear","strTeam");
$resposta = array("strCountry","intStadiumCapacity","strStadiumLocation","strStadium", "strTeam","strTeam","strTeam","strLeague","intFormedYear","strTeam");
$pergunta1 =array("Qual o país do time ","Qual a capacidade do estádio do time ", "Qual a cidade do time ", "Qual o nome do estádio do time ", "O estádio da imagem é de qual time","Esse escudo da imagem é de qual time","A camisa da imagem é de qual time","Qual a liga principal do time ","Qual o ano de formação do time ","Qual o time tem origem no(a) ");
$pergunta2 = array("strTeam","strTeam","strTeam","strTeam","strNull","strNull","strNull","strTeam","strTeam","strCountry");
$imagem = array("strNull","strNull","strNull","strNull","strStadiumThumb","strTeamBadge","strTeamJersey","strNull","strNull","strNull");

$arraysPergunta1 = geraPerguntasRespostas($tipo[0],$resposta[0],$pergunta1[0],$pergunta2[0],$imagem[0]);//ok
$arraysPergunta2 = geraPerguntasRespostas($tipo[1],$resposta[1],$pergunta1[1],$pergunta2[1],$imagem[1]);//ok
$arraysPergunta3 = geraPerguntasRespostas($tipo[2],$resposta[2],$pergunta1[2],$pergunta2[2],$imagem[2]);//ok
$arraysPergunta4 = geraPerguntasRespostas($tipo[3],$resposta[3],$pergunta1[3],$pergunta2[3],$imagem[3]);//ok
$arraysPergunta5 = geraPerguntasRespostas($tipo[4],$resposta[4],$pergunta1[4],$pergunta2[4],$imagem[4]);//ok
$arraysPergunta6 = geraPerguntasRespostas($tipo[5],$resposta[5],$pergunta1[5],$pergunta2[5],$imagem[5]);//ok
$arraysPergunta7 = geraPerguntasRespostas($tipo[6],$resposta[6],$pergunta1[6],$pergunta2[6],$imagem[6]);//ok
$arraysPergunta8 = geraPerguntasRespostas($tipo[7],$resposta[7],$pergunta1[7],$pergunta2[7],$imagem[7]);//ok
$arraysPergunta9 = geraPerguntasRespostas($tipo[8],$resposta[8],$pergunta1[8],$pergunta2[8],$imagem[8]);//ok
$arraysPergunta10 = geraPerguntasRespostas($tipo[9],$resposta[9],$pergunta1[9],$pergunta2[9],$imagem[9]);//ok


$perguntas = array_merge($arraysPergunta1,$arraysPergunta2,$arraysPergunta3,$arraysPergunta4,$arraysPergunta5,$arraysPergunta6,$arraysPergunta7,$arraysPergunta8,$arraysPergunta9,$arraysPergunta10);

foreach ($perguntas as $key => $value) {
    $perguntas[$key] = array('id'=> $key + 1) + $perguntas[$key];
}

$perguntas_identificador = array('perguntas'=> $perguntas);
$perguntas_json = json_encode($perguntas_identificador, JSON_UNESCAPED_SLASHES | JSON_UNESCAPED_UNICODE);
$criarJason = fopen("perguntas.json","a");
$escreveJson = fwrite($criarJason,$perguntas_json);
fclose($criarJason);
echo "Arquivo 'perguntas_json.json' criado na pasta.";