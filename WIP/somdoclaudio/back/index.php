<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require 'vendor/autoload.php';

function getConnection() {
	$dbhost="127.0.0.1";
	$dbuser="root";
	$dbpass="";
	$dbname="somdoclaudio";
	$dbh = new PDO("mysql:host=$dbhost;dbname=$dbname", $dbuser, $dbpass);
	$dbh->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	return $dbh;
}

// INICIALIZAR
$app = new \Slim\App();



////////////////////////////////////////////////////
//////                  ROTAS                ///////
////////////////////////////////////////////////////
// Lista de usuarios
$app->get('/users', function() {
	$sql = "select * FROM usuario ORDER BY IDusuario";
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

// Traz um usuario
$app->get('/user/{id}', function (Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$sql = "select * FROM usuario WHERE IDusuario=".$id;
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

// retorna o relacionamento entre dois usuários
$app->get('/relacionamento/{id}/{id2}', function (Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$id2 = $request->getAttribute('id2');

	$sql = "select acao, relacTipo,IDusuario,R_U_IDusuario FROM relacionamento
	WHERE (IDusuario=".$id." OR R_U_IDusuario=".$id.") AND (IDUsuario=".$id2." OR R_U_IDusuario=".$id2.")";
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});
//SOLICITAR AMIZADE
$app->post('/relacionamento/solicitar/{id}/{id2}', function (Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$id2 = $request->getAttribute('id2');
	$sql = "select acao, relacTipo,IDusuario,R_U_IDusuario FROM relacionamento
	WHERE (IDusuario=".$id." OR R_U_IDusuario=".$id.") AND (IDUsuario=".$id2." OR R_U_IDusuario=".$id2.");";
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		if($wines==null)
		{
			$sql = "insert into relacionamento(acao,relacTipo,IDUsuario,R_U_IDusuario) values (".$id.",0,".$id.",".$id2.");";
			try {
			$db = getConnection();
			$stmt = $db->query($sql);
			$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
			$db = null;
			echo json_encode($wines);
			} catch(PDOException $e) {
			echo '{"error":{"text":'. $e->getMessage() .'}}';
		}

		}
	} catch(PDOException $e) {

	}

});

$app->delete('/relacionamento/excluir/{id}/{id2}', function(Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$id2 = $request->getAttribute('id2');
	$user = json_decode($request->getBody());
	$sql = "Delete from relacionamento
	WHERE (IDusuario=".$id." OR R_U_IDusuario=".$id.") AND (IDUsuario=".$id2." OR R_U_IDusuario=".$id2.")";
	try {
		$db = getConnection();
		$stmt = $db->prepare($sql);
		$stmt->execute();
		$db = null;
		echo json_encode($user);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

// Update user
$app->put('/user/{id}', function(Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$user = json_decode($request->getBody());
	$sql = "UPDATE usuario SET userNome=:username, email=:email, userLocalizacao=:localization WHERE IDusuario=:id";
	try {
		$db = getConnection();
		$stmt = $db->prepare($sql);
		$stmt->bindParam("username", $user->userNome);
		$stmt->bindParam("email", $user->email);
		$stmt->bindParam("localization", $user->userLocalizacao);
		$stmt->bindParam("id", $id);
		$stmt->execute();
		$db = null;
		echo json_encode($user);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

//
// // Add user
// $app->post('/add_user', function (Request $request, Response $response) {
// 	$user = json_decode($request->getBody());
// 	$sql = "INSERT INTO usuario (userNome, email, senha, dataNasci, userLocalizacao, Longitude, Latitude, dataIngresso) VALUES (:username, :first_name, :last_name, :address)";
// 	try {
// 		$db = getConnection();
// 		$stmt = $db->prepare($sql);
// 		$stmt->bindParam("username", $user->username);
// 		$stmt->bindParam("first_name", $user->first_name);
// 		$stmt->bindParam("last_name", $user->last_name);
// 		$stmt->bindParam("address", $user->address);
// 		$stmt->execute();
// 		$user->id = $db->lastInsertId();
// 		$db = null;
// 		echo json_encode($user);
// 	} catch(PDOException $e) {
// 		echo '{"error":{"text":'. $e->getMessage() .'}}';
// 	}
// });
//
//
// // Delete user
// $app->delete('/users/{id}', function(Request $request, Response $response) {
// 	$id = $request->getAttribute('id');
// 	$sql = "DELETE FROM users WHERE id=".$id;
// 	try {
// 		$db = getConnection();
// 		$stmt = $db->query($sql);
// 		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
// 		$db = null;
// 		echo json_encode($wines);
// 	} catch(PDOException $e) {
// 		echo '{"error":{"text":'. $e->getMessage() .'}}';
// 	}
// });




// POSTS
$app->get('/posts', function() {
	$sql = "select usuario.idusuario,usuario.userNome,post.postConteudo,HOUR(TIMEDIFF(now(),post.postdata)) as tempo FROM post
	left join postmusica on postmusica.idpost = post.idpost
	left join postevento on postevento.idpost = post.idpost
	left join postusuario on postevento.idpost = post.idpost
	left join postplaylist on postplaylist.idpost = post.idpost
	inner join usuario on usuario.IDusuario = post.IDusuario
	WHERE post.idusuario = ANY
	(
		SELECT IDusuario from relacionamento
		where (IDusuario=1 or R_U_IDusuario =1) AND relacTipo=1
		UNION
		Select R_U_IDusuario from relacionamento
		where (IDusuario=1 or R_U_IDusuario =1) AND relacTipo=1
	)
	ORDER BY postData DESC";
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

$app->get('/posts/user/{id}', function(Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$sql = "select usuario.idusuario,usuario.userNome,post.postConteudo,HOUR(TIMEDIFF(now(),post.postdata)) as tempo FROM post
	left join postmusica on postmusica.idpost = post.idpost
	left join postevento on postevento.idpost = post.idpost
	left join postusuario on postevento.idpost = post.idpost
	left join postplaylist on postplaylist.idpost = post.idpost
	inner join usuario on usuario.IDusuario = post.IDusuario
	where post.idUsuario=".$id." order by post.postData DESC";
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

$app->post('/post/user/{id}', function (Request $request, Response $response) {
	$idUser = $request->getAttribute('id');
	$event = json_decode($request->getBody());
	$sql = "INSERT INTO post (IDusuario, postConteudo, postData) VALUES (:idUser, :content, NOW())";
	try {
		$db = getConnection();
		$stmt = $db->prepare($sql);
		$stmt->bindParam("idUser", $idUser);
		$stmt->bindParam("content", $event->postConteudo);
		$stmt->execute();
		$event->id = $db->lastInsertId();
		$db = null;

		$sql = "INSERT INTO postMusica (IDpost, caminhoMusica) VALUES (:idPost, :hrefMusic)";
		try {
			$db = getConnection();
			$stmt = $db->prepare($sql);
			$stmt->bindParam("idPost", $event->id );
			$stmt->bindParam("hrefMusic", $event->caminhoMusica);
			$stmt->execute();
			$db = null;
		} catch (PDOException $e) {
			echo '{"error":{"text":'. $e->getMessage() .'}}';

		}
		echo json_encode($event);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

// Eventos
$app->get('/events', function() {
	$sql = "select * from evento";
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;

		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

$app->get('/event/{id}', function (Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$sql = "select * FROM evento WHERE IDevento=".$id;
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

$app->put('/event/{id}', function(Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$user = json_decode($request->getBody());
	$sql = "UPDATE evento SET eventoNome=:name, eventoData=:dateEvent, eventoHorario=:hour, eventoLocalizacao=:localization, eventoDescricao=:description WHERE IDevento=:id";
	try {
		$db = getConnection();
		$stmt = $db->prepare($sql);
		$stmt->bindParam("name", $user->eventoNome);
		$stmt->bindParam("dateEvent", $user->eventoData);
		$stmt->bindParam("hour", $user->eventoHorario);
		$stmt->bindParam("localization", $user->eventoLocalizacao);
		$stmt->bindParam("description", $user->eventoDescricao);
		$stmt->bindParam("id", $id);
		$stmt->execute();
		$db = null;
		echo json_encode($user);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

$app->post('/event/create', function (Request $request, Response $response) {
	$event = json_decode($request->getBody());
	$sql = "INSERT INTO evento (eventoNome, eventoData, eventoHorario, eventoLocalizacao, eventoDescricao) VALUES (:name, :dateEvent, NOW(), :localization, :description)";
	try {
		$db = getConnection();
		$stmt = $db->prepare($sql);
		$stmt->bindParam("name", $event->eventoNome);
		$stmt->bindParam("dateEvent", $event->eventoData);
		// $stmt->bindParam("hour", $event->eventoHorario);
		$stmt->bindParam("localization", $event->eventoLocalizacao);
		$stmt->bindParam("description", $event->eventoDescricao);
		$stmt->execute();
		$event->id = $db->lastInsertId();
		$db = null;

		$sql = "INSERT INTO cria (IDevento, IDusuario) VALUES (:idEvent, :idUser)";
		try {
			$db = getConnection();
			$stmt = $db->prepare($sql);
			$stmt->bindParam("idEvent", $event->id);
			$stmt->bindParam("idUser", $event->IDusuario);
			$stmt->execute();
			$event->id = $db->lastInsertId();
			$db = null;

		} catch (PDOException $e) {
			echo '{"error":{"text":'. $e->getMessage() .'}}';

		}


		echo json_encode($event);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});

$app->delete('/event/{id}', function(Request $request, Response $response) {
	$id = $request->getAttribute('id');
	$sql = "DELETE FROM evento WHERE IDevento=".$id;
	try {
		$db = getConnection();
		$stmt = $db->query($sql);
		$wines = $stmt->fetchAll(PDO::FETCH_OBJ);
		$db = null;
		echo json_encode($wines);
	} catch(PDOException $e) {
		echo '{"error":{"text":'. $e->getMessage() .'}}';
	}
});





$app->run();
?>
