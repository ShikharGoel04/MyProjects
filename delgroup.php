<?php
require "init.php";
$name=$_POST["name"];
$id=$_POST["id"];
$sql="select groupid from registration where name like'".$name."'";
$result=mysqli_query($con,$sql);
$idd=array();
$row=mysqli_fetch_row($result);
$idd=$row[0];
$arr=array();
$arr=explode(" ",$idd);
$cnt=sizeof($arr);
$id2="";
$s=0;
while($s<$cnt)
{
$id1=$arr[$s];
if($id1!=$id)
{
if($id2=="")
{
$id2=$id1;
}
else
{
$id2.=" ".$id1;
}
}
$s=$s+1;
}
$ins="Update registration SET groupid='".$id2."' where name like '".$name."'";
$resultins=mysqli_query($con,$ins);
$memb="select GroupMembers from diffgroups where GroupID='".$id."'";
$memb3=array();
$memb1=mysqli_query($con,$memb);
$memb2=mysqli_fetch_row($memb1);
$memb3=$memb2[0];
$memb4=array();
$memb4=explode(" ",$memb3);
$memb5=sizeof($memb4);
$t=0;
$mem="";
while($t<$memb5)
{
$id4=$memb4[$t];
if($id4!=$name)
{
if($mem=="")
{
$mem=$id4;
}
else
{
$mem.=" ".$id4;
}
}
$t=$t+1;
}
$ins10="Update diffgroups SET GroupMembers='".$mem."' where GroupID='".$id."'";
$resultins10=mysqli_query($con,$ins10);
if($mem=="")
{
$ins1="delete from diffgroups where GroupID='".$id."'";
$result45=mysqli_query($con,$ins1);
}
$response=array();
if($resultins && $resultins10)
{
$success="successfully deleted group";
array_push($response,array("success"=>$success));
echo json_encode($response);
}



?>