<?php
  $file1 = '0205886159.jpg';
  $title1 = 'Global Issues, Local Arguments';
  $quantity1 = 25;
  $price1 = 25;


  $file2 = '0205875548.jpg';
  $title2 = 'The Prentice Hall Guide for College Writers';
  $quantity2 = 50;
  $price2 = 50;


  $file3 = '0321826035.jpg';
  $title3 = 'Introductory and Intermediate Algebra 5e';
  $quantity3 = 40;
  $price3 = 35;


  $file4 = '0205902278.jpg';
  $title4 = 'Literature and the Writing Process';
  $quantity4 = 300;
  $price4 = 20;


  //-----------------------------------------上面为lab8的数据，下面为lab9的数据--------------------------------------------

  $continents = array("Asia","Africa","Europe","North America","South America", "Oceania");

  $countries = array();
  $countries["DE"] = "Germany";
  $countries["GR"] = "Greece";
  $countries["IT"] = "Italy";
  $countries["CA"] = "Canada";
  $countries["GB"] = "United Kingdom";
  $countries["US"] = "United States";


  $images = array();
  $images[22] = array("id"=>22,"title" => "View of Cologne", "description" => "View of Cologne from atop the Cologne Cathedral", "country" => "Germany", "city" => "Cologne", "user" => "Joao Fernandes", "path" => "6114850721.jpg", "taken" => "August 8, 2017", "tags" => array("cathedral","high","birds-eye"));

  $images[54] = array("id"=>54,"title" => "Arch of Septimus Severus", "description" => "In the Roman Forum", "country" => "Italy", "city" => "Rome", "user" => "Ellie Sullivan", "path" => "9495574327.jpg", "taken" => "August 15, 2017", "tags" => array("rome","ruins","ancient","assassinated"));

  $images[7] = array("id"=>7,"title" => "Lunenburg Port", "description" => "On board a small sailing ship leaving Lunenburg", "country" => "Canada", "city" => "Lunenburg", "user" => "Mark Taylor", "path" => "5856697109.jpg", "taken" => "August 28, 2017", "tags" => array("maritimes","nova scotia","yacht","port"));

  $images[19] = array("id"=>19,"title" => "British Museum", "description" => "The library in the British Museum in London", "country" => "United Kingdom", "city" => "London", "user" => "Mark Taylor", "path" => "5855729828.jpg", "taken" => "September 3, 2017", "tags" => array("museum","karl marx","reading room","shadows","yellow"));

  $images[46] = array("id"=>46,"title" => "Temple of Hephaistos", "description" => "Located on western perimeter of Agora in Athens. Built in 460-415 BCE, it is the best preserved temple of antiquity.", "country" => "Greece", "city" => "Athens", "user" => "Ellie Sullivan", "path" => "8711645510.jpg", "taken" => "October 10, 2017", "tags" => array("ancient","garden","ruins","sunshine"));

  $images[6] = array("id"=>6,"title" => "At the top of Sulpher Mountain", "description" => "At top of Sulpher Mountain near Banff", "country" => "Canada", "city" => "Banff", "user" => "Frantisek Wichterlova", "path" => "6114907897.jpg", "taken" => "October 15, 2017", "tags" => array("mountain","banff","valley"));

  $images[60] = array("id"=>60,"title" => "Pazzi Chapel at Santa Croce", "description" => "By Filippo Brunelleschi, the chapel is considered to be one of the masterpieces of Renaissance architecture. It is located in the first cloister of the Basilica di Santa Croce.", "country" => "Italy", "city" => "Florence", "user" => "Frantisek Wichterlova", "path" => "9504609042.jpg", "taken" => "October 23, 2017", "tags" => array("florence","cathedral","church","brunelleschi"));

  $images[58] = array("id"=>58,"title" => "Florence Duomo", "description" => "Photo taken from the Campanile", "country" => "Italy", "city" => "Florence", "user" => "Ellie Sullivan", "path" => "9498358806.jpg", "taken" => "November 4, 2017", "tags" => array("cathedral","church","brunelleschi","vertigo"));

  $images[75] = array("id"=>75,"title" => "Ancient Theatre of Dionysos", "description" => "On south bank of Acropolis", "country" => "Greece", "city" => "Athens", "user" => "Mark Taylor", "path" => "8710513053.jpg", "taken" => "November 6, 2017", "tags" => array("athens","ruins","theatre","sophocles"));

  $images[77] = array("id"=>77,"title" => "Dusk on Santorini", "description" => "Looking towards Imerovigli, a village devoted to the appreciation of the sunset!", "country" => "Greece", "city" => "Fira", "user" => "Camille Bernard", "path" => "8710247776.jpg", "taken" => "November 6, 2017", "tags" => array("fira","sunset","beautiful","wow","volcano"));

  $images[27] = array("id"=>27,"title" => "New National Gallery", "description" => "Exterior of the Mies van der Rohe designed New National Gallery", "country" => "Germany", "city" => "Berlin", "user" => "Joao Fernandes", "path" => "6114867983.jpg", "taken" => "August 12, 2017", "tags" => array("museum","berlin","modernism","architecture"));

  $images[2] = array("id"=>2,"title" => "Calgary Downtown", "description" => "Calgary Downtown from Edworthy Park", "country" => "Canada", "city" => "Calgary", "user" => "Camille Bernard", "path" => "6592914823.jpg", "taken" => "October 23, 2017", "tags" => array("autumn","leaves","skyscrappers","zoom"));


  $images[24] = array("id"=>24,"title" => "Downtown Frankfurt", "description" => "Downtown Frankfurt from the Frankfurt Cathedral", "country" => "Germany", "city" => "Frankfurt", "user" => "Ellie Sullivan", "path" => "6114960821.jpg", "taken" => "August 4, 2017", "tags" => array("downtown","skyscrapper","architecture"));

  $images[13] = array("id"=>13,"title" => "Central Park", "description" => "Conservatory Pond in Central Park New York", "country" => "United States", "city" => "New York", "user" => "Mark Taylor", "path" => "6596048341.jpg", "taken" => "September 9, 2017", "tags" => array("downtown","skyscrapper","architecture"));

  $images[101] = array("id"=>101,"title" => "Seattle Scene", "description" => "View of the Space Needle and the EMP Museum", "country" => "United States", "city" => "Seattle", "user" => "Mark Taylor", "path" => "21587937686.jpg", "taken" => "September 23, 2017", "tags" => array("downtown","skyscrapper","architecture","modernism"));

  $images[102] = array("id"=>102,"title" => "Millennium Park Chicago", "description" => "Jay Pritzker Pavilion at Millennium Park by Frank Gehry", "country" => "United States", "city" => "Chicago", "user" => "Mark Taylor", "path" => "22182041615.jpg", "taken" => "September 28, 2017", "tags" => array("downtown","skyscrapper","architecture","modernism"));

?>
