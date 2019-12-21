<?php
require "init.php";
$id=$_POST["id"];
$sql="delete from post where id ='".$id."'";
$result=mysqli_query($con,$sql);
$response=array();
if($result)
{
$success="successfully deleted";
$message="deleted successfully";
array_push($response,array("success"=>$success,"message"=>$message));
echo json_encode($response);
}
{
$success="delete failed";
$message="not deleted successfully";
array_push($response,array("success"=>$success,"message"=>$message));
echo json_encode($response);
}
?>