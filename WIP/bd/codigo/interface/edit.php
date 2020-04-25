<?php require_once('header.php'); ?>

<?php
include_once 'dbconfig.php';
if(isset($_GET['id']))
{
  // $taqui = ($_GET['id']
  $sql_query="SELECT * FROM intercambio WHERE NumInter=".$_GET['id'];
  $result_set=mysql_query($sql_query);
  $fetched_row=mysql_fetch_array($result_set);
}



if(isset($_POST['action']))
{
  $id = $_GET['id'];
  $nome = $_POST['nome'];
  $tipo = $_POST['tipo'];
  $vagas = $_POST['vagas'];
  $pais = $_POST['pais'];
  $universidade = $_POST['universidade'];
  $inicio = $_POST['inicio'];
  $fim = $_POST['fim'];


  // $sql_query = "INSERT INTO `intercambio` (`NumInter`, `NomeInter`, `TipoInter`, `NumVagas`, `Pais`, `Universidade`, `InterInicio`, `InterFim`) VALUES
  // ('$id', '$nome', '$tipo', '$vagas', '$pais', '$universidade', '$inicio', '$fim')";

  $sql_query = "UPDATE `intercambio` SET
  NomeInter='$nome',TipoInter='$tipo',NumVagas='$vagas',Pais='$pais',Universidade='$universidade',InterInicio='$inicio',InterFim='$fim'
  WHERE NumInter=".$id;

  $results = mysql_query($sql_query);
}
?>


<div class="row white "style="
padding-top: 50px;">

<span class="title-header">Editar</span>
<form method="post" class="col s12">
  <div class="row">
    <div class="input-field col s6">
      <input id="NomeInter" value="<?php echo $fetched_row['NomeInter']; ?>" name="nome" type="text">
      <label for="NomeInter">Nome intercambio</label>
    </div>
    <div class="input-field col s6">
        <input id="NomeInter" value="<?php echo $fetched_row['TipoInter']; ?>" name="tipo" type="text">
        <label for="NomeInter">Tipo intercambio</label>
        <!-- <select name="tipo" class="browser-default">
          <option value="<?php echo $fetched_row['TipoInter']; ?>" disabled selected>Selecione a tipo de intercambio</option>
          <option value="Indicação">Indicação</option>
          <option value="Intercâmbio">Intercâmbio</option>
          <option value="Bolsas - Práticas de Ensino">Bolsas - Práticas de Ensino</option>
          <option value="Curso Presencial - Language Education">Curso Presencial - Language Education</option>
        </select> -->
    </div>
  </div>
  <div class="row">
    <div class="input-field col s6">
      <input id="pais" name="vagas" value="<?php echo $fetched_row['NumVagas']; ?>"  type="text">
      <label for="pais">Número de vagas</label>
    </div>
    <div class="input-field col s6">
      <input id="pais" name="pais" value="<?php echo $fetched_row['Pais']; ?>" type="text">
      <label for="pais">País</label>
    </div>
    <div class="input-field col s6">
      <input id="universidade" name="universidade" value="<?php echo $fetched_row['Universidade']; ?>" type="text">
      <label for="universidade">Universidade</label>
    </div>
    <div class="input-field col s6">
      <span for="">Inicio intercambio</span>
      <input name="inicio"  type="date" value="<?php echo $fetched_row['InterInicio']; ?>" class="datepicker">
    </div>
    <div class="input-field col s6">
      <p for="">Fim intercambio</p>
      <input name="fim" type="date" value="<?php echo $fetched_row['InterFim']; ?>" class="datepicker">
    </div>
  </div>
  <button class="btn waves-effect waves-light right" type="submit" name="action">Salvar
  </button>
  <br><br><br>
</form>
</div>
