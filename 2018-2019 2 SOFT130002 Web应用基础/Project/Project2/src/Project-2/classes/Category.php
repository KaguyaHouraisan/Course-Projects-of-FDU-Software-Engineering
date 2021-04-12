<?php

class Category {

    private $_db, $_subCategory, $_category;

    public function __construct() {
        $this->_db = DB::getInstance();
        $this->_category = $this->_db->action("SELECT *", "ss_category", array('1', '=', '1'));
    }

    public function exist() {
        return (!empty($this->_category)) ? true : false;
    }

    public function get() {
        return $this->_category;
    }

    public function rows() {
        return $this->_category->count();
    }

    public function subCategory($parentId) {
        $this->_subCategory = $this->_db->get("ss_sub_category", array('category_id', '=', "$parentId"));
        return $this->_subCategory;
    }

    public function subRows() {
        return $this->_subCategory->count();
    }
}