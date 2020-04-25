<?php require_once('header.php');
include_once 'dbconfig.php';

if(isset($_GET['delete_id']))
{
  $sql_query="DELETE FROM intercambio WHERE NumInter=".$_GET['delete_id'];
  mysql_query($sql_query);
  header("Location: $_SERVER[PHP_SELF]");
}
?>

<div class=" m30">
  <div class="card blue-grey lighten-5">
    <div class="card-content">
      <a href="new" class="btn right blue darken-3">Novo intercambio</a>
      <span class="card-title"><h4 class="title-table">Lista de intercambio</h4></span>
      <table class="bordered striped">
        <thead>
          <tr>
            <th>Nome </th>
            <th>Tipo</th>
            <th>Vagas</th>
            <th>País</th>
            <th>Universidade</th>
            <th>Inicio</th>
            <th>Fim</th>
            <th></th

            </tr>
          </thead>
          <tbody>
            <tr >
              <?php
              $sql_query="SELECT * FROM intercambio";
              $result_set=mysql_query($sql_query);
              while($row=mysql_fetch_row($result_set))
              {
                ?>
                <tr>
                  <td><?php echo $row[1]; ?></td>
                  <td><?php echo $row[2]; ?></td>
                  <td><?php echo $row[3]; ?></td>
                  <td><?php echo $row[4]; ?></td>
                  <td><?php echo $row[5]; ?></td>
                  <td><?php echo $row[6]; ?></td>
                  <td><?php echo $row[7]; ?></td>
                  <td>
                    <a href="studentsList?id=<?php echo $row[0]; ?>" class="btn btn-small blue-grey darken-4">Alunos</a><div class="clearfix"></div>
                    <a href="edit?id=<?php echo $row[0]; ?>" class="btn btn-small blue-grey darken-4" >Editar</a><div class="clearfix"></div>
                    <a href="?delete_id=<?php echo $row[0]; ?>" class="btn btn-small blue-grey darken-4"  >Excluir</a>
                  </td>
                </tr>
                <?php
              }
              ?>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</body>
</html>
