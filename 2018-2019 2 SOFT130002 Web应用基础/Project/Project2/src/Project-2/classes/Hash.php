<?php

class Hash {
    public static function make($string, $salt = '') {
        return hash('sha256', $string . $salt);
    }

    public static function salt($length) {
        $str = 'qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890';
        return substr(str_shuffle($str),10,$length);
    }

    public static function unique() {
        return self::make(uniqid());
    }
}