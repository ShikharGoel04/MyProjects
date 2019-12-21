<?php
require "init.php";
$id=$_POST["id"];
$sql="select name,post,id from post where idd='".$id."'";
$result=mysqli_query($con,$sql);
$s=mysqli_num_rows($result);
$response=array();
while($s)
{
$row=mysqli_fetch_row($result);
$name=$row[0];
$post=$row[1];
$idd1=$row[2];
array_push($response,array("name"=>$name,"post"=>$post,"idd1"=>$idd1));
$s=$s-1;
}
echo json_encode($response);
mysqli_close($con);

?>