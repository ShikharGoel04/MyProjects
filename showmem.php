<?php
require "init.php";
$id=$_POST["id"];
$sql="select GroupMembers from diffgroups where GroupID='".$id."'";
$result=mysqli_query($con,$sql);
$row=mysqli_fetch_row($result);
$response=array();
$response=$row[0];
$arr=array();
$arr=explode(" ",$response);
$cnt=sizeof($arr);
$s=0;
$ans=array();
while($s<$cnt)
{
$gm=$arr[$s];
array_push($ans,array("GroupMembers"=>$gm));
$s=$s+1;
}
echo json_encode($ans);

?>