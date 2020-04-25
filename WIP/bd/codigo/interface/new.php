<?php require_once('header.php'); ?>

<?php
include_once 'dbconfig.php';
if(isset($_POST['action']))
{
  $result = mysql_query("SELECT * FROM intercambio");
  // $num_rows = mysql_num_rows($result);
  $id=0;
  while($row=mysql_fetch_row($result))
  {
    if($id < $row[0]) $id = $row[0];
  }
  $id = $id + 1;
  $nome = $_POST['nome'];
  $tipo = $_POST['tipo'];
  $vagas = $_POST['vagas'];
  $pais = $_POST['pais'];
  $universidade = $_POST['universidade'];
  $inicio = $_POST['inicio'];
  $fim = $_POST['fim'];


  $sql_query = "INSERT INTO `intercambio` (`NumInter`, `NomeInter`, `TipoInter`, `NumVagas`, `Pais`, `Universidade`, `InterInicio`, `InterFim`) VALUES
  ('$id', '$nome', '$tipo', '$vagas', '$pais', '$universidade', '$inicio', '$fim')";

  $results = mysql_query($sql_query);
}
?>


<div class="row white "style="
padding-top: 50px;">

<span class="title-header">Novo intercambio</span>
<form method="post" class="col s12">
  <div class="row">
    <div class="input-field col s6">
      <input id="NomeInter" name="nome" type="text">
      <label for="NomeInter">Nome intercambio</label>
    </div>
    <div class="input-field col s6">
      <input id="NomeInter" name="tipo" type="text">
      <label for="NomeInter">Tipo intercambio</label>
      <!-- <select name="tipo" class="browser-default">
        <option value="" disabled selected>Selecione a tipo de intercambio</option>
        <option value="1">Indicação</option>
        <option value="2">Intercâmbio</option>
        <option value="3">Bolsas - Práticas de Ensino</option>
        <option value="4">Curso Presencial - Language Education</option>
      </select> -->
    </div>
  </div>
  <div class="row">
    <div class="input-field col s6">
      <input id="pais" name="vagas"  type="text">
      <label for="pais">Número de vagas (Insire apenas numero)</label>
    </div>
    <div class="input-field col s6">
      <input id="pais" name="pais"  type="text">
      <label for="pais">País</label>
    </div>
    <div class="input-field col s6">
      <input id="universidade" name="universidade"  type="text">
      <label for="universidade">Universidade</label>
    </div>
    <div class="input-field col s6">
      <span for="">Inicio intercambio</span>
      <input name="inicio"  type="date" class="datepicker">
    </div>
    <div class="input-field col s6">
      <p for="">Fim intercambio</p>
      <input name="fim" type="date" class="datepicker">
    </div>
  </div>
  <button class="btn waves-effect waves-light right" type="submit" name="action">Criar
  </button>
  <br><br><br>
</form>
</div>
