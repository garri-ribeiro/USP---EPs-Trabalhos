<?php require_once('header.php'); ?>
<?php
include_once 'dbconfig.php';
if(isset($_GET['id']))
{
  $sql_query="SELECT I.Nome, I.CPF FROM individuo as I WHERE I.CPF IN ( SELECT A.CPF FROM aluno as A, intercambista, intercambio WHERE ( intercambista.CPF = A.CPF AND intercambista.NumInter =".$_GET['id']."))";
  $result_set=mysql_query($sql_query);
}
if(isset($_POST['post_aluno']))
{
  $CPF = $_POST['option'];
  $id = $_GET['id'];
  $sql_query2="INSERT INTO `intercambista` (`CPF`, `NumInter`) VALUES ('$CPF','$id')";
  $seinao=mysql_query($sql_query2);
}
if(isset($_POST['delete_id']))
{
  $delete="DELETE FROM intercambista WHERE CPF=".$_POST['delete_id'];
  mysql_query($delete);
}
$listaAlunos="SELECT  individuo.nome, individuo.CPF  FROM individuo, aluno WHERE individuo.CPF = aluno.CPF";
$resultListaAlunos = mysql_query($listaAlunos);

?>

<div class="row">
  <ul class="collection with-header">
    <li class="collection-header blue-grey lighten-5"><h4>Alunos inscritos no intercambio</h4></li>
    <?php
    while($row=mysql_fetch_row($result_set))
    {
      ?>
      <li class="collection-item">
        <div class="row">
          <div class="col s8">
            <?php echo $row[0]; ?>
          </div>
          <div class="col s4">
            <form class="" method="post">
              <button value="<?php echo $row[1]; ?>" name="delete_id" type="submit "class="btn btn-small blue-grey darken-4 right">Retirar</button>
            </form>
          </div>
        </div>
      </li>
      <?php
    }
    ?>
  </ul>
</div>
<div class="row">
  <div class="col s4 blue-grey lighten-5">
    <form class="" method="post">
      <div class="row">
        <div class="col s12">
          <h5>Inscrever novo aluno</h5>
        </div>
        <div class="input-field col s12">
          <select class="browser-default" name="option">
            <?php
            while($row=mysql_fetch_row($resultListaAlunos))
            {
              ?>
              <option value="<?php echo $row[1]; ?>"><?php echo $row[0]; ?></option>
            </li>
            <?php
          }
          ?>
        </select>
      </div>
    </div>

    <button class="btn waves-effect waves-light right" type="submit" name="post_aluno">Inscrever aluno
    </button>
    <br><br>
  </form>

  </div>
</div>
