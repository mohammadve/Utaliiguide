package com.utaliiguides.models.signUpQuestion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Ffareaey {

    private String name;
    private String email;
    private String mobile_no;
    private String password;
    private String lang;
    private String gender;
    private String device_token;
    private String guide_private_price;
    private String guide_pool_price;
    private String guide_about;
    private String guideaddress;
    private String tourist_identity_card_no;
    private File identity_front;
    private File identity_back;
    private String localid_proof_no;
    private File localid_front;
    private File localid_back;
    private String driving_licenceid;
    private File driving_licence_front;
    private File driving_licence_back;
    private String questions;
    private String otp;
    private String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getGuide_private_price() {
        return guide_private_price;
    }

    public void setGuide_private_price(String guide_private_price) {
        this.guide_private_price = guide_private_price;
    }

    public String getGuide_pool_price() {
        return guide_pool_price;
    }

    public void setGuide_pool_price(String guide_pool_price) {
        this.guide_pool_price = guide_pool_price;
    }

    public String getGuide_about() {
        return guide_about;
    }

    public void setGuide_about(String guide_about) {
        this.guide_about = guide_about;
    }

    public String getGuideaddress() {
        return guideaddress;
    }

    public void setGuideaddress(String guideaddress) {
        this.guideaddress = guideaddress;
    }

    public String getTourist_identity_card_no() {
        return tourist_identity_card_no;
    }

    public void setTourist_identity_card_no(String tourist_identity_card_no) {
        this.tourist_identity_card_no = tourist_identity_card_no;
    }

    public File getIdentity_front() {
        return identity_front;
    }

    public void setIdentity_front(File identity_front) {
        this.identity_front = identity_front;
    }

    public File getIdentity_back() {
        return identity_back;
    }

    public void setIdentity_back(File identity_back) {
        this.identity_back = identity_back;
    }

    public String getLocalid_proof_no() {
        return localid_proof_no;
    }

    public void setLocalid_proof_no(String localid_proof_no) {
        this.localid_proof_no = localid_proof_no;
    }

    public File getLocalid_front() {
        return localid_front;
    }

    public void setLocalid_front(File localid_front) {
        this.localid_front = localid_front;
    }

    public File getLocalid_back() {
        return localid_back;
    }

    public void setLocalid_back(File localid_back) {
        this.localid_back = localid_back;
    }

    public String getDriving_licenceid() {
        return driving_licenceid;
    }

    public void setDriving_licenceid(String driving_licenceid) {
        this.driving_licenceid = driving_licenceid;
    }

    public File getDriving_licence_front() {
        return driving_licence_front;
    }

    public void setDriving_licence_front(File driving_licence_front) {
        this.driving_licence_front = driving_licence_front;
    }

    public File getDriving_licence_back() {
        return driving_licence_back;
    }

    public void setDriving_licence_back(File driving_licence_back) {
        this.driving_licence_back = driving_licence_back;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
