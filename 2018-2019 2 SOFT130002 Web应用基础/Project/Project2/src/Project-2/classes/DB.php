<?php

class DB {
    private static $_instance = null;
    private $_pdo,
            $_query,
            $_error = false,
            $_results,
            $_count = 0;

    private function __construct() {
        try {
            $this->_pdo = new PDO('mysql:host=' . Config::get('mysql/host') . ';dbname=' . Config::get('mysql/db'), Config::get('mysql/username'), Config::get('mysql/password'));
        } catch(PDOException $e) {
            die($e->getMessage());
        }
    }

    public static function getInstance() {
        if(!isset(self::$_instance)) {
            self::$_instance = new DB();
        }
        return self::$_instance;
    }

    public function query($sql, $params = array()) {
        $this->_error = false;
        if($this->_query = $this->_pdo->prepare($sql)) {
            $x = 1;
            if(count($params)) {
                foreach($params as $param) {
                    $this->_query->bindValue($x, $param);
                    $x++;
                }
            }

            if($this->_query->execute()) {
                $this->_results = $this->_query->fetchAll(PDO::FETCH_OBJ);
                $this->_count = $this->_query->rowCount();
            } else {
                $this->_error = true;
            }
        }

        return $this;
    }

    public function orderAction($action, $table, $or, $limit = 30) {
        $sql = "{$action} FROM {$table} ORDER BY {$or} LIMIT {$limit}";

        if(!$this->query($sql, array(1))->error()) {
            return $this;
        }
        return false;
    }

    public function action($action, $table, $where = array(), $limit = 30) {
        if(count($where) === 3) {
            $operators = array('=', '>', '<', '>=', '<=');

            $field = $where[0];
            $operator = $where[1];
            $value = $where[2];

            if(in_array($operator, $operators)) {
                $sql = "{$action} FROM {$table} WHERE {$field} {$operator} ? LIMIT {$limit}";

                if(!$this->query($sql, array($value))->error()) {
                    return $this;
                }
            }
        }
        return false;
    }

    public function get($table, $where) {
        return $this->action('SELECT *', $table, $where);
    }

    public function delete($table, $where) {
        return $this->action('DELETE', $table, $where);
    }

    #insert data in table
    public function insert($table, $fields = array()) {
        $keys = array_keys($fields);
        $values = '';
        $x = 1;

        foreach($fields as $field) {
            $values .= '?';
            if($x < count($fields)) {
                $values .= ', ';
            }
            $x++;
        }

        $sql = "INSERT INTO {$table} (`" . implode('`, `', $keys) . "`) VALUES ({$values})";

        if(!$this->query($sql, $fields)->error()) {
            return true;
        }
        return false;
    }

    public function insertUser($table, $fields = array()) {
        $values = "null, '" . $fields['username'] . "', '" . $fields['password'] . "', '" . $fields['salt'] . "', '". $fields['joined'] . "', '". $fields['email'] . "', '" . $fields['mobile'] . "', '" . $fields['address'] . "', 0";
        echo $values;
        $sql = "INSERT INTO {$table} VALUES ({$values})";

        if(!$this->query($sql, $fields)->error()) {
            return true;
        }
        return false;
    }

    # Update table
    public function update($table, $id, $fields) {
        $set = '';
        $x = 1;

        foreach($fields as $name => $value) {
            $set .= "{$name} = ?";
            if($x < count($fields)) {
                $set .= ', ';
            }
            $x++;
        }

        $sql = "UPDATE {$table} SET {$set} WHERE id = {$id}";

        if(!$this->query($sql, $fields)->error()) {
            return true;
        }
        return false;
    }

    public function updatePopIndex($artworkID, $fields) {
        $t = $fields['artworkID'] + 1;
        $sql = "UPDATE artworks SET view = {$t} WHERE artworkID = {$artworkID}";

        if(!$this->query($sql, $fields)->error()) {
            return true;
        }
        return false;
    }

    public function results() {
        return $this->_results;
    }

    public function first() {
        return $this->results()[0];
    }

    public function error() {
        return $this->_error;
    }

    public function count() {
        return $this->_count;
    }

    public function searchEngine($table, $col, $pattern) {
        $sql = "SELECT * FROM `{$table}` WHERE `{$col}` LIKE '%{$pattern}%'";

        if(!$this->query($sql)->error()) {
            if($this->_count > 0) {
                $data = "<table class='table table-bordered table-hover'>";
                for($x=0; $x<$this->_count; $x++) {
                    $data .= "<tr><td>{$this->results()[$x]->$col}</td></tr>";
                }
                $data .= "</table>";

                return $data;
            }
        }
        return false;
    }

    public function lastId() {
        return $this->_pdo->lastInsertId();
    }
}