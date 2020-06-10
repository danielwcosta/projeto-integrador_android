<?php
$urlBrasilA = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4351";
$urlBrasilB = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4404";
$urlInglaterra = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4328";
$urlAlemanha = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4331";
$urlFranca = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4334";
$urlEspanha = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4335";
$urlItalia = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4332";
$urlArgentina = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4406";

function pegandoJson($url){
    $arquivo = file_get_contents($url);
    $arrayArquivo = json_decode($arquivo);
    return $arrayArquivo->teams;
}

$arrayBrasilA = pegandoJson($urlBrasilA);
$arrayBrasilB = pegandoJson($urlBrasilB);
$arrayInglaterra = pegandoJson($urlInglaterra);
$arrayAlemanha = pegandoJson($urlAlemanha);
$arrayFranca = pegandoJson($urlFranca);
$arrayEspanha = pegandoJson($urlEspanha);
$arrayItalia = pegandoJson($urlItalia);
$arrayArgentina = pegandoJson($urlArgentina);

$arrayTimes = array_merge($arrayBrasilA,$arrayBrasilB,$arrayInglaterra,$arrayAlemanha,$arrayFranca,$arrayEspanha,$arrayItalia,$arrayArgentina);
//print_r($arrayTimes);

foreach($arrayTimes as $key=>$time){
    
    $arrayTimesFiltrados[$key] = array(
        'id'                =>  $key,
        'strTeam'           =>  $time->strTeam,
        'strCountry'        =>  $time->strCountry,
        'intStadiumCapacity'=>  $time->intStadiumCapacity,
        'strStadiumLocation'=>  $time->strStadiumLocation,
        'strStadium'        =>  $time->strStadium,
        'strStadiumThumb'   =>  $time->strStadiumThumb,
        'strTeamBadge '     =>  $time->strTeamBadge ,
        'strTeamJersey'     =>  $time->strTeamJersey,
        'strLeague'         =>  $time->strLeague,
        'intFormedYear'     =>  $time->intFormedYear,
        'strAlternate'      =>  $time->strAlternate,
        'strRSS'            =>  $time->strRSS,
        'strWebsite'        =>  $time->strWebsite,
        'strFacebook'       =>  $time->strFacebook,
        'strTwitter'        =>  $time->strTwitter,
        'strInstagram'      =>  $time->strInstagram,
        'strDescriptionPT'  =>  $time->strDescriptionPT,
        'strYoutube'        =>  $time->strYoutube,
        'strNull'           =>  null
  );
  }



$timesFiltrados_identificador = array('times'=> $arrayTimesFiltrados);
$times_filtrados_json = json_encode($timesFiltrados_identificador, JSON_UNESCAPED_SLASHES | JSON_UNESCAPED_UNICODE);
$criarJason = fopen("timesfiltrados.json","a");
$escreveJson = fwrite($criarJason,$times_filtrados_json);
fclose($criarJason);
echo "Arquivo 'timesfiltrados.json' criado na pasta.";
?>