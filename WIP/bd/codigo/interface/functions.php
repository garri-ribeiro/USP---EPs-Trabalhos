
<?php
if(isset($_POST['action']))
{
  $result = mysql_query("SELECT * FROM intercambio");
  $num_rows = mysql_num_rows($result);

  $id = $num_rows + 1;
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
