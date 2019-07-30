package com.example.emon.bdmedic.LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Html;
import android.widget.Toast;

import com.example.emon.bdmedic.AppUserData;
import com.example.emon.bdmedic.ContactList.Android_Contact;

import com.example.emon.bdmedic.ContactList.PojoClass.ContactData;
import com.example.emon.bdmedic.ContactList.PojoClass.ContactPjo;
import com.example.emon.bdmedic.DoctorAndChamber.ChamberData;
import com.example.emon.bdmedic.DoctorAndChamber.DoctorData;
import com.example.emon.bdmedic.Fragments.BranchData;
import com.example.emon.bdmedic.Fragments.BriefData;
import com.example.emon.bdmedic.Fragments.DisciplineData;
import com.example.emon.bdmedic.Fragments.DiseaseManageData;
import com.example.emon.bdmedic.Fragments.RevisedByData;
import com.example.emon.bdmedic.Fragments.TreatmentMedicineData;
import com.example.emon.bdmedic.Laboratory.InvestigationData;
import com.example.emon.bdmedic.Laboratory.PojoClass.HospitalData;
import com.example.emon.bdmedic.Laboratory.PojoClass.InvestigationPriceData;
import com.example.emon.bdmedic.Laboratory.PojoClass.MedicinePharmacologyData;
import com.example.emon.bdmedic.Laboratory.PojoClass.PharmacologyInfoData;
import com.example.emon.bdmedic.Laboratory.PojoClass.SubPharmacologyData;
import com.example.emon.bdmedic.Media.MediaPojoClass.MediaData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.CategoryData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SponsorData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemCategoryData;
import com.example.emon.bdmedic.MedicineGroupPojoClasses.SystemData;
import com.example.emon.bdmedic.MenuFeed;
import com.example.emon.bdmedic.Pharmacologist.MedicineSubGroupData;
import com.example.emon.bdmedic.PriceCalculator.DosageData;
import com.example.emon.bdmedic.PriceCalculator.MedicineData;

import java.util.List;

public class MyDBHelper extends SQLiteOpenHelper {

//    private static final String DATABASE_NAME = "bdmedics_app";
    private static final String DATABASE_NAME = "bdmedics_database";
    private static final String MEDICINES = "medicines";
    private static final String MEDICINES_DOSAGE = "medicines_dosage";
    private static final String MEDICINE_SUBGROUP = "medicine_subgroup";
    private static final String DISEASE_MANAGEMENT = "disease_management";
    private static final String BRANCH_AND_DISCIPLINE = "branch_and_discipline";
    private static final String SPONSOR = "sponsor";
    private static final String MEDIA = "media";
    private static final String FAVOURITE = "favourite";
    private static final String user_info = "user_info";

    private static final String BRANCH = "branch";
    private static final String DISCIPLINE = "discipline";
    private static final String BRIEF = "brief";
    private static final String TREATMENT_AND_MEDICINE = "treatment_and_medicine";
    private static final String INVESTIGATION = "investigation";
    private static final String CONTACT_LIST = "contact_list";
    private static final String DOCTOR = "doctor";
    private static final String CHAMBER = "chamber";
    private static final String REVISED_BY = "revised_by";
    private static final String DATA_SYNC = "data_sync";
    private static final String SYSTEM = "system";
    private static final String CATEGORY = "category";
    private static final String SYSTEM_CATEGORY = "system_category";
    private static final String HOSPITAL = "hospital";
    private static final String INVESTIGATION_PRICE = "investigation_price";
    private static final String MEDICINE_PHARMACOLOGY = "medicine_pharmacology";
    private static final String PHARMACOLOGY_INFO = "pharmacology_info";
    private static final String SUB_PHARMACOLOGY = "sub_pharmacology";



    private static final String DROP_TABLE_CONTACT_LIST = "DROP TABLE IF EXISTS " + CONTACT_LIST;
    private static final String DROP_TABLE_MEDICINES = "DROP TABLE IF EXISTS " + MEDICINES;
    private static final String DROP_TABLE_MEDICINES_DOSAGE = "DROP TABLE IF EXISTS " + MEDICINES_DOSAGE;
    private static final String DROP_TABLE_MEDICINE_SUBGROUP = "DROP TABLE IF EXISTS " + MEDICINE_SUBGROUP;
    private static final String DROP_TABLE_DISEASE_MANAGEMENT = "DROP TABLE IF EXISTS " + DISEASE_MANAGEMENT;
    private static final String DROP_TABLE_SPONSOR = "DROP TABLE IF EXISTS " + SPONSOR;
    private static final String DROP_TABLE_MEDIA = "DROP TABLE IF EXISTS " + MEDIA;
    private static final String DROP_TABLE_FAVOURITE = "DROP TABLE IF EXISTS " + FAVOURITE;
    private static final String DROP_TABLE_user_info = "DROP TABLE IF EXISTS " + user_info;
    private static final String DROP_TABLE_BRANCH = "DROP TABLE IF EXISTS " + BRANCH;
    private static final String DROP_TABLE_DISCIPLINE = "DROP TABLE IF EXISTS " + DISCIPLINE;
    private static final String DROP_TABLE_BRIEF = "DROP TABLE IF EXISTS " + BRIEF;
    private static final String DROP_TABLE_TREATMENT_AND_MEDICINE = "DROP TABLE IF EXISTS " + TREATMENT_AND_MEDICINE;
    private static final String DROP_TABLE_INVESTIGATION = "DROP TABLE IF EXISTS " + INVESTIGATION;
    private static final String DROP_TABLE_DOCTOR = "DROP TABLE IF EXISTS " + DOCTOR;
    private static final String DROP_TABLE_CHAMBER = "DROP TABLE IF EXISTS " + CHAMBER;
    private static final String DROP_TABLE_REVISED_BY = "DROP TABLE IF EXISTS " + REVISED_BY;
    private static final String DROP_TABLE_DATA_SYNC = "DROP TABLE IF EXISTS " + DATA_SYNC;
    private static final String DROP_TABLE_SYSTEM = "DROP TABLE IF EXISTS " + SYSTEM;
    private static final String DROP_TABLE_SYSTEM_CATEGORY = "DROP TABLE IF EXISTS " + SYSTEM_CATEGORY;
    private static final String DROP_TABLE_HOSPITAL = "DROP TABLE IF EXISTS " + HOSPITAL;
    private static final String DROP_TABLE_INVESTIGATION_PRICE = "DROP TABLE IF EXISTS " + INVESTIGATION_PRICE;
    private static final String DROP_TABLE_MEDICINE_PHARMACOLOGY = "DROP TABLE IF EXISTS " + MEDICINE_PHARMACOLOGY;
    private static final String DROP_TABLE_PHARMACOLOGY_INFO = "DROP TABLE IF EXISTS " + PHARMACOLOGY_INFO;
    private static final String DROP_TABLE_SUB_PHARMACOLOGY = "DROP TABLE IF EXISTS " + SUB_PHARMACOLOGY;





//    private static final int VERSION = 53;
    private static final int VERSION = 1;
    private static String ID = "id";
    private static final String medicine_sub_group_id = "medicine_subgroup_id";
    private static final String company_id = "company_id";
    private static final String sub_generic_name = "sub_generic_name";
    private static final String name = "name";
    private static final String added_by = "added_by";
    private static final String description = "description";
    private static final String created_at = "created_at";
    private static final String upadated_at = "updated_at";
    private static final String COMPANY_NAME = "company_name";
    private static final String id = "_id";
    private static final String medicine_id = "medicine_id";
    private static final String generic_type = "generic_type";
    private static final String specific_type = "specific_type";
    private static final String UNIT = "UNIT";
    private static final String package_method = "package_method";
    private static final String package_price = "package_price";
    private static final String unit_price = "unit_price";
    private static final String __id = "__id";
    private static final String system = "system";
    private static final String category = "category";
    private static final String generic_name = "generic_name";
    private static final String pregnency_category = "pregnency_category";
    private static final String disease_name = "disease_name";
    private static final String branch_id = "branch_id";
    private static final String patient_category = "patient_category";
    private static final String patient_sample = "patient_sample";
    private static final String pregnancy = "pregnancy";
    private static final String age = "age";
    private static final String weight = "weight";
    private static final String investigation_id = "investigation_id";
    private static final String analysis = "analysis";
    private static final String advice = "advice";
    private static final String history = "history";
    private static final String sign = "sign";
    private static final String type = "type";

    private static final String symtom = "symtom";
    private static final String on_examination = "on_examination";
    private static final String note = "note";
    private static final String template_type = "template_type";
    public static final String discpline_id = "discpline_id";
    public static final String branch_name = "branch_name";
    public static final String status = "status";
    public static final String disease_management_id = "disease_management_id";
    public static final String brief = "brief";
    public static final String treat_brief_id = "treat_brief_id";
    public static final String dosage_id = "dosage_id";
    public static final String sponsor_rank = "sponsor_rank";
    public static final String dose_frequency = "dose_frequency";
    public static final String relation = "relation";
    public static final String normal_finding = "normal_finding";
    public static final String increased = "increased";
    public static final String decreased = "decreased";
    public static final String others = "others";
    public static final String number = "number";
    public static final String blood_group = "blood_group";


    public static final String phone = "phone";
    public static final String sex = "sex";
    public static final String present_dist = "present_dist";
    public static final String present_upa = "present_upa";
    public static final String permanent_dist = "permanent_dist";
    public static final String permanent_upa = "permanent_upa";
    public static final String user_type = "user_type";
    public static final String email = "email";
    public static final String fb_link = "fb_link";
    public static final String medical_id = "medical_id";
    public static final String batch_no = "batch_no";
    public static final String roll_no = "roll_no";
    //    public static final String status = "status";
    public static final String reg_type = "reg_type";
    public static final String doctor_id = "doctor_id";
    public static final String bmdc_reg_no = "bmdc_reg_no";
    public static final String designation = "designation";
    public static final String speciality = "speciality";
    public static final String academic_career = "academic_career";
    public static final String post_grad_branch_1 = "post_grad_branch_1";
    public static final String post_grad_branch_2 = "post_grad_branch_2";
    public static final String medicine_subgroup_id = "medicine_subgroup_id";


    public static final String chamber_address = "chamber_address";
    public static final String cham_dist = "cham_dist";
    public static final String cham_upa = "cham_upa";
    public static final String contact_no = "contact_no";
    public static final String chamber_opens = "chamber_opens";
    public static final String updated_date = "updated_date";
    public static final String system_id = "system_id";
    public static final String category_id = "category_id";
    public static final String supplier_id = "supplier_id";
    public static final String rank = "rank";
//HOSPITAL_TABLE......
public static final String contact = "contact";
public static final String address = "address";
public static final String hos_dist = "hos_dist";
public static final String hos_upa = "hos_upa";


//INVESTIGATION_PRICE_TABLE

    public static final String hospital_id = "hospital_id";
    public static final String price = "price";


    //

    public static final String pharmacology_id = "pharmacology_id";
    public static final String sub_pharmacology_id = "sub_pharmacology_id";

    //
    public static final String sub_pharmacologies_id = "sub_pharmacologies_id";

    //
    public static final String parent_pharmacologies_id = "parent_pharmacologies_id";
    DisciplineData disciplineData;
    ChamberData chamberData;
    MedicineData medicineDatas;
//    Activity activity;
//    public static final String medicine_id = "medicine_id";

    private static final String createTableMedicines = "CREATE TABLE if not EXISTS " + MEDICINES + "(" + ID + " INTEGER PRIMARY KEY ,"
            + name + " VARCHAR(191),"
            + sub_generic_name + " VARCHAR(100),"
            + COMPANY_NAME + " VARCHAR(100),"
            + company_id + " VARCHAR(100),"
            + medicine_sub_group_id + " VARCHAR(100))";



    public static final String user_token= "user_token";
    private static String verification = "verification";
    private static final String createTableUserInfo = "CREATE TABLE if not EXISTS " + user_info + "(" + ID + " INTEGER PRIMARY KEY ,"
            + name + " VARCHAR(191),"
            + sex + " VARCHAR(100),"
            + phone + " VARCHAR(100),"
            + blood_group + " Integer,"
            + present_dist + " Integer,"
            +present_upa+" Integer,"
           + permanent_dist+" Integer,"
           + permanent_upa+"  Integer,"
            +user_type+" Integer,"
            +email+" VARCHAR(50),"
            +fb_link+"  VARCHAR(50) ,"
            +medical_id+" Integer,"
            +batch_no+" Integer,"
            +roll_no+" Integer,"
            +status+" Integer,"
            +reg_type+" Integer ,"
            +user_token+" longtext,"
            +verification+" VARCHAR(20))";


    public static final String favouriteItem = "favourite_item";

    /*private static final String createTableFavourite = "CREATE TABLE  " + FAVOURITE + "("
            + ID + " Integer PRIMARY KEY AUTOINCREMENT ,"
            + status + " INTEGER,"
            ++"
            )";*/
    private static final String click_id = "click_id";

    private static final String createTableFavourite = "CREATE TABLE if not EXISTS " + FAVOURITE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + click_id + " INTEGER,"
            + type + " VARCHAR(100),"
            + status + " INTEGER)";

    private static String url = "url";
    private static final String createTableMedia = "CREATE TABLE if not EXISTS " + MEDIA + "(" + ID + " INTEGER PRIMARY KEY ,"
            + name + " VARCHAR(191),"
            + type + " INTEGER,"
            + url + " VARCHAR(200))";


    private static final String createTableHospital = "CREATE TABLE if not EXISTS " + HOSPITAL + "(" + ID + " INTEGER PRIMARY KEY ,"
            + name + " VARCHAR(191),"
            + contact + " VARCHAR(100),"
            + address + " VARCHAR(100),"
            + hos_dist + " INTEGER,"
            + hos_upa + " INTEGER(100),"
            +status+" INTEGER)";

    public static final String createTableInvestigationPrice = "CREATE TABLE if not EXISTS " + INVESTIGATION_PRICE + "(" + ID + " INTEGER PRIMARY KEY ,"
            + investigation_id + " INTEGER,"
            + hospital_id + " INTEGER,"
            + price + " VARCHAR(100))";

    public static final String createTableMedicinePharmacology = "CREATE TABLE if not EXISTS " + MEDICINE_PHARMACOLOGY + "(" + ID + " INTEGER PRIMARY KEY ,"
            + medicine_sub_group_id + " INTEGER,"
            + pharmacology_id + " INTEGER,"
            + description + " longtext,"
            + sub_pharmacology_id + " INTEGER)";

    public static final String createPharmacologyInfo = "CREATE TABLE if not EXISTS " + PHARMACOLOGY_INFO + "(" + ID + " INTEGER PRIMARY KEY ,"
            + name + " VARCHAR(191),"
            + sub_pharmacologies_id + " INTEGER,"
            + rank + " INTEGER)";

    public static final String createSubPharmacology = "CREATE TABLE if not EXISTS " + SUB_PHARMACOLOGY + "(" + ID + " INTEGER  PRIMARY KEY ,"
            + name + " VARCHAR(191),"
            + parent_pharmacologies_id + " INTEGER,"
            + rank + " INTEGER)";

    public static final String createTableSponsor = "CREATE TABLE if not EXISTS " + SPONSOR + "(" + ID + " INTEGER PRIMARY KEY ,"
            + supplier_id + " Integer,"
            + rank + " Integer)";

    public static  final String createTableSystem =  "CREATE TABLE if not EXISTS " + SYSTEM + "(" + ID + " INTEGER PRIMARY KEY ,"
            + name + " VARCHAR(191))";
    public static  final String createTableCategory =  "CREATE TABLE if not EXISTS " + CATEGORY + "(" + ID + " INTEGER PRIMARY KEY ,"
            + name + " VARCHAR(191))";

    public static  final String createTableSystemCategory =  "CREATE TABLE if not EXISTS " + SYSTEM_CATEGORY + "(" + ID + " INTEGER PRIMARY KEY ,"
            + system_id + " INTEGER,"+category_id+" INTEGER)";

    public static final String createTableMedicineSubgroup = "Create table if not Exists " + MEDICINE_SUBGROUP + "(" + ID + " Integer ,"
            + category + " varchar(150),"
            + generic_name + " varchar(150),"
            + pregnency_category + " varchar(150),"
            +medicine_subgroup_id+" INTEGER PRIMARY KEY)";
    //private static final String createTableMedicineDosage = "CREATE TABLE "+MEDICINES_DOSAGE+"("+id+" Integer primary key autoincrement,"+specific_type+"varchar(50),"+generic_type+"varchar(50),"+UNIT+"varchar(50),"+package_method+"varchar(50),"+package_price+"varchar(50),"+unit_price+"varchar(50));";
    public static final String createTableMedicineDosage = "Create table if not EXISTS " + MEDICINES_DOSAGE + "(_id Integer primary key,medicine_id Integer ,specific_type varchar(100),generic_type varchar(100),unit varchar(100),package_method varchar(100),package_price varchar(100),unit_price varchar(100))";
    public static final String createTableDiseaseMangement = "CREATE TABLE if not EXISTS " + DISEASE_MANAGEMENT + "(_id Integer primary key ,disease_name varchar(150),branch_id Integer,category varchar(150),patient_category Integer,patient_sample Integer,pregnancy Integer,age Integer,weight Double,investigation_id Integer,analysis varchar(150),advice varchar(150),history varchar(150),sign varchar(150),symtom varchar(150),on_examination varchar(150),note varchar(150),template_type varchar(150))";
    public static final String createTableDiscipline = "CREATE TABLE if not EXISTS " + DISCIPLINE + "(" + ID + " Integer primary key,"
            + status + " Integer,"
            + name + " varchar(150))";
    public static final String createTableBranch = "CREATE TABLE if not EXISTS " + BRANCH + "(" + ID + " Integer primary key,"
            + discpline_id + " Integer,"
            + branch_name + " varchar(150),"
            + status + " Integer)";
    public static final String createTableBrief = "CREATE TABLE if not EXISTS " + BRIEF + "(" + ID + " Integer primary key,"
            + disease_management_id + " Integer,"
            + brief + " longtext)";

    public static final String createTableTREATMENT_AND_MEDICINE = "CREATE TABLE if not EXISTS " + TREATMENT_AND_MEDICINE + "(" + ID + " INTEGER PRIMARY KEY,"
            + treat_brief_id + " INTEGER,"
            + medicine_id + " INTEGER,"
            + dosage_id + " INTEGER,"
            + sponsor_rank + " INTEGER,"
            + dose_frequency + " VARCHAR(150),"
            + relation + " varchar(50))";

    public static final String createTableINVESTIGATION = "CREATE TABLE if not EXISTS "
            + INVESTIGATION + "("
            + ID + " INTEGER PRIMARY KEY,"
            + name + " varchar(150),"
            + normal_finding + " varchar(150),"
            + increased + " varchar(150),"
            + decreased + " varchar(150),"
            + others + " varchar(150))";

    public static final String createTableCONTACT_LIST = "CREATE TABLE if not EXISTS "
            + CONTACT_LIST + "("
            + name + " varchar(150),"
            + number + " varchar(20) primary key,"
            + blood_group + " varchar(10))";

    public static final String createTableDOCTOR = "CREATE TABLE if not EXISTS "
            + DOCTOR + "("
            + ID + " INTEGER PRIMARY KEY,"
            + name + " varchar(100),"
            + phone + " varchar(20),"
            + sex + " varchar(10),"
            + blood_group + " varchar(10),"
            + present_dist + " varchar(50),"
            + present_upa + " varchar(50),"
            + permanent_dist + " varchar(50),"
            + permanent_upa + " varchar(50),"
            + user_type + " Integer,"
            + email + " varchar(150),"
            + fb_link + " varchar(150),"
            + medical_id + " Integer,"
            + batch_no + " varchar(50),"
            + roll_no + " varchar(100),"
            + status + " Integer,"
            + reg_type + " Integer,"
            + doctor_id + " Integer,"
            + bmdc_reg_no + " varchar(100),"
            + designation + " varchar(50),"
            + speciality + " varchar(50),"
            + academic_career + " varchar(150),"
            + post_grad_branch_1 + " Integer,"
            + post_grad_branch_2 + " Integer)";

    public static final String createTableCHAMBER = "CREATE TABLE if not EXISTS "
            + CHAMBER + "("
            + ID + " INTEGER PRIMARY KEY,"
            + doctor_id + " INTEGER,"
            + chamber_address + " varchar(150),"
            + cham_dist + " varchar(50),"
            + cham_upa + " varchar(50),"
            + contact_no + " varchar(20),"
            + chamber_opens + " varchar(100),"
            + status + " Integer)";

    public static final String createTableREVISED_BY = "CREATE TABLE  if not EXISTS "
            + REVISED_BY + "("
            + ID + " INTEGER PRIMARY KEY,"
            + disease_management_id + " INTEGER,"
            + doctor_id + " INTEGER)";


    public static final String createTableDATA_SYNC = "CREATE TABLE if not EXISTS " + DATA_SYNC + "(" + ID + " INTEGER primary key, " + updated_date + " datetime)";


    Context context;

    public static final String TRUNCATE = "DELETE FROM " + MEDICINES;

    SQLiteDatabase database;


    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }

//    private static final String createTableMedicineDosage = "CREATE TABLE " +MEDICINES_DOSAGE+"("+id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+generic_type+"VARCHAR(100),"+specific_type+" VARCHAR(50)," +UNIT+" VARCHAR(50)," +package_method+" VARCHAR(50)," +package_price+" VARCHAR(50)," +unit_price+" VARCHAR(50));";

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {



            db.execSQL(createTableMedicineSubgroup);
            db.execSQL(createTableDiseaseMangement);
            db.execSQL(createTableDiscipline);
            db.execSQL(createTableBranch);
            db.execSQL(createTableBrief);
            db.execSQL(createTableMedicines);
            db.execSQL(createTableTREATMENT_AND_MEDICINE);
            db.execSQL(createTableINVESTIGATION);
            db.execSQL(createTableCONTACT_LIST);
            db.execSQL(createTableDOCTOR);
            db.execSQL(createTableCHAMBER);
            db.execSQL(createTableREVISED_BY);
            db.execSQL(createTableDATA_SYNC);
            db.execSQL(createTableSystem);
            db.execSQL(createTableCategory);
            db.execSQL(createTableSystemCategory);
            db.execSQL(createTableSponsor);
            db.execSQL(createTableHospital);
            db.execSQL(createTableInvestigationPrice);
            db.execSQL(createTableMedicinePharmacology);
            db.execSQL(createPharmacologyInfo);
            db.execSQL(createSubPharmacology);
            db.execSQL(createTableMedicineDosage);
            db.execSQL(createTableMedia);
            db.execSQL(createTableFavourite);
            db.execSQL(createTableUserInfo);

        } catch (Exception e) {
            Toast.makeText(context, "Exception " + e, Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Toast.makeText(context, "onUpgrade method called", Toast.LENGTH_LONG).show();
//        db.execSQL(TRUNCATE);
//        db.execSQL(VACUUM);
//        db.execSQL(DROP_TABLE);
//        db.execSQL(DROP_TABLE);
//         db.execSQL(DROP_TABLE);



/*
        db.execSQL(DROP_TABLE_CONTACT_LIST);
        db.execSQL(DROP_TABLE_MEDICINES);
        db.execSQL(DROP_TABLE_MEDICINES_DOSAGE);
        db.execSQL(DROP_TABLE_MEDICINE_SUBGROUP);
        db.execSQL(DROP_TABLE_DISEASE_MANAGEMENT);
        db.execSQL(DROP_TABLE_SPONSOR);
        db.execSQL(DROP_TABLE_MEDIA);
        db.execSQL(DROP_TABLE_FAVOURITE);
        db.execSQL(DROP_TABLE_user_info);
        db.execSQL(DROP_TABLE_BRANCH);
        db.execSQL(DROP_TABLE_DISCIPLINE);
        db.execSQL(DROP_TABLE_BRIEF);
        db.execSQL(DROP_TABLE_TREATMENT_AND_MEDICINE);
        db.execSQL(DROP_TABLE_INVESTIGATION);
        db.execSQL(DROP_TABLE_DOCTOR);
        db.execSQL(DROP_TABLE_CHAMBER);
        db.execSQL(DROP_TABLE_REVISED_BY);
        db.execSQL(DROP_TABLE_DATA_SYNC);
        db.execSQL(DROP_TABLE_SYSTEM);
        db.execSQL(DROP_TABLE_SYSTEM_CATEGORY);
        db.execSQL(DROP_TABLE_HOSPITAL);
        db.execSQL(DROP_TABLE_INVESTIGATION_PRICE);
        db.execSQL(DROP_TABLE_MEDICINE_PHARMACOLOGY);
        db.execSQL(DROP_TABLE_PHARMACOLOGY_INFO);
        db.execSQL(DROP_TABLE_SUB_PHARMACOLOGY);
        onCreate(db);*/


    }

    public void insertDiscipline(List<DisciplineData> sData) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(name, sData.get(i).getName());
            contentValues.put(status, sData.get(i).getStatus());

//            rowID = sqLiteDatabase.insert(DISCIPLINE, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(DISCIPLINE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(DISCIPLINE,contentValues,ID+"="+disciplineData.getId(),null);
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(DISCIPLINE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(DISCIPLINE,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : discipline InvestigationData insert successfully");

        }
        sqLiteDatabase.close();


    }

    public void insertFavourite(String favType, int clickId) {

        long rowID;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in favourite");
        ContentValues contentValues = new ContentValues();

        contentValues.put(type,favType);
        contentValues.put(click_id,clickId);
        contentValues.put(status,1);

        rowID = sqLiteDatabase.insert(FAVOURITE, null, contentValues);
           /* int rowID = (int) sqLiteDatabase.insertWithOnConflict(FAVOURITE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(FAVOURITE,contentValues,"favourite_item =?",new String[]{favourite_item});
            }*/
            System.out.println(rowID + " : favourite insert successfully");
        sqLiteDatabase.close();


    }

    public void updateFavourite(int id, int Status) {

        long rowID;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in update favourite");
        ContentValues contentValues = new ContentValues();
        contentValues.put(status,Status);

        rowID = sqLiteDatabase.update(FAVOURITE,contentValues,"id=?",new String[]{Integer.toString(id)});

        System.out.println(rowID + " : favourite update successfully");
        sqLiteDatabase.close();


    }
    public int insertBranch(List<BranchData> sData) {

        /*menuFeed.hideLayout.setVisibility(View.GONE);
        menuFeed.progressLayout.setVisibility(View.VISIBLE);*/
//        menuFeed.circularProgressBar.enableIndeterminateMode(true);
//        circularProgressBar.enableIndeterminateMode(true);
        System.out.println("Enter in branch...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(discpline_id, sData.get(i).getDiscplineId());
            contentValues.put(branch_name, sData.get(i).getBranchName());
            contentValues.put(status, sData.get(i).getStatus());
//            rowID = sqLiteDatabase.insert(BRANCH, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(BRANCH, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(BRANCH,contentValues,ID+"="+sData.get(i).getId(),null);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(BRANCH, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(BRANCH,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : Branch InvestigationData insert successfully");
          /*  menuFeed.hideLayout.setVisibility(View.VISIBLE);
            menuFeed.progressLayout.setVisibility(View.GONE);*/

        }
        sqLiteDatabase.close();
        return 1;

    }

    public void insertBrief(List<BriefData> sData) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        System.out.println("Enter in brief");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(disease_management_id, sData.get(i).getDiseaseManagementId());
            contentValues.put(brief, sData.get(i).getBrief());

//            rowID = sqLiteDatabase.insert(BRIEF, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(BRIEF, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(BRIEF,contentValues,ID+"="+sData.get(i).getId(),null);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(BRIEF, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
               rowID = sqLiteDatabase.update(BRIEF,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : brief InvestigationData insert successfully");

        }

        sqLiteDatabase.close();

    }

    public void insertTreatmentMedicine(List<TreatmentMedicineData> sData) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in trtmntMedicine");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(treat_brief_id, sData.get(i).getTreatBriefId());
            contentValues.put(medicine_id, sData.get(i).getMedicineId());
            contentValues.put(dosage_id, sData.get(i).getDosageId());
            contentValues.put(sponsor_rank, sData.get(i).getSponsorRank());
            contentValues.put(dose_frequency, sData.get(i).getDoseFrequency());
            contentValues.put(relation, sData.get(i).getRelation());
//            rowID = sqLiteDatabase.insert(TREATMENT_AND_MEDICINE, null, contentValues);

//            rowID = sqLiteDatabase.insertWithOnConflict(TREATMENT_AND_MEDICINE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(TREATMENT_AND_MEDICINE,contentValues,ID+"="+sData.get(i).getId(),null);
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(TREATMENT_AND_MEDICINE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(TREATMENT_AND_MEDICINE,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : trtmntMedicine InvestigationData insert successfully");

        }

        sqLiteDatabase.close();


    }

    public void insertDoctor(List<DoctorData> doctorDataList) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in Doctor");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < doctorDataList.size(); i++) {
            System.out.println("Enter in Doctor  1");
            contentValues.put(ID, doctorDataList.get(i).getId());
            System.out.println("Enter in Doctor 2 ");
            contentValues.put(name, doctorDataList.get(i).getName());
            System.out.println("Enter in Doctor 3");
            contentValues.put(phone, doctorDataList.get(i).getPhone());
            System.out.println("Enter in Doctor 4");
            contentValues.put(sex, doctorDataList.get(i).getSex());
            System.out.println("Enter in Doctor 5");
            contentValues.put(blood_group, doctorDataList.get(i).getBloodGroup());
            System.out.println("Enter in Doctor 6 ");
            contentValues.put(present_dist, doctorDataList.get(i).getPresentDist());
            System.out.println("Enter in Doctor 7");
            contentValues.put(present_upa, doctorDataList.get(i).getPresentUpa());
            System.out.println("Enter in Doctor 8");
            contentValues.put(permanent_dist, doctorDataList.get(i).getPermanentDist());
            System.out.println("Enter in Doctor 9 ");
            contentValues.put(permanent_upa, doctorDataList.get(i).getPermanentUpa());
            System.out.println("Enter in Doctor 10");
            contentValues.put(user_type, doctorDataList.get(i).getUserType());
            System.out.println("Enter in Doctor  11");
            contentValues.put(email, doctorDataList.get(i).getEmail());
            System.out.println("Enter in Doctor 12");
            contentValues.put(fb_link, doctorDataList.get(i).getFbLink());
            System.out.println("Enter in Doctor 13");
            contentValues.put(medical_id, doctorDataList.get(i).getMedicalId());
            System.out.println("Enter in Doctor 14");
            contentValues.put(batch_no, doctorDataList.get(i).getBatchNo());
            contentValues.put(roll_no, doctorDataList.get(i).getRollNo());
            contentValues.put(status, doctorDataList.get(i).getStatus());
            contentValues.put(reg_type, doctorDataList.get(i).getRegType());
            contentValues.put(doctor_id, doctorDataList.get(i).getDoctorId());
            contentValues.put(bmdc_reg_no, doctorDataList.get(i).getBmdcRegNo());
            contentValues.put(designation, doctorDataList.get(i).getDesignation());
            contentValues.put(speciality, doctorDataList.get(i).getSpeciality());
            contentValues.put(academic_career, doctorDataList.get(i).getAcademicCareer());
            contentValues.put(post_grad_branch_1, doctorDataList.get(i).getPostGradBranch1());
            contentValues.put(post_grad_branch_2, doctorDataList.get(i).getPostGradBranch2());


//            rowID = sqLiteDatabase.insert(DOCTOR, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(DOCTOR, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(DOCTOR,contentValues,ID+"="+doctorDataList.get(i).getId(),null);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(DOCTOR, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(DOCTOR,contentValues,"id=?",new String[]{Integer.toString(doctorDataList.get(i).getId())});
            }

            System.out.println(rowID + " : doctor InvestigationData insert successfully");

        }
        sqLiteDatabase.close();

    }

    public void insertUserInfo(List<AppUserData> doctorDataList) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in user info");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < doctorDataList.size(); i++) {

            System.out.println("Enter in user info 2");
            contentValues.put(ID, doctorDataList.get(i).getId());
            System.out.println("Enter in user info 3");
            contentValues.put(name, doctorDataList.get(i).getName());
            System.out.println("Enter in user info  4");
            contentValues.put(phone, doctorDataList.get(i).getPhone());
            System.out.println("Enter in user info 5 ");
            contentValues.put(sex, doctorDataList.get(i).getSex());
            System.out.println("Enter in user info 56");
            contentValues.put(blood_group, doctorDataList.get(i).getBloodGroup());
            System.out.println("Enter in user info 7");
            contentValues.put(present_dist, doctorDataList.get(i).getPresentDist());
            System.out.println("Enter in user info8");
            contentValues.put(present_upa, doctorDataList.get(i).getPresentUpa());
            System.out.println("Enter in user info 9");
            contentValues.put(permanent_dist, doctorDataList.get(i).getPermanentDist());
            System.out.println("Enter in user info 10");
            contentValues.put(permanent_upa, doctorDataList.get(i).getPermanentUpa());
            System.out.println("Enter in user info 11 ");
            contentValues.put(user_type, doctorDataList.get(i).getUserType());
            contentValues.put(email, doctorDataList.get(i).getEmail());
            contentValues.put(fb_link, doctorDataList.get(i).getFbLink());
            contentValues.put(medical_id, doctorDataList.get(i).getMedicalId());
            contentValues.put(batch_no, doctorDataList.get(i).getBatchNo());
            contentValues.put(roll_no, doctorDataList.get(i).getRollNo());
            contentValues.put(status, doctorDataList.get(i).getStatus());
            contentValues.put(reg_type, doctorDataList.get(i).getRegType());
            contentValues.put(user_token, doctorDataList.get(i).getUserToken());
            contentValues.put(verification, doctorDataList.get(i).getVerification());



//            rowID = sqLiteDatabase.insert(DOCTOR, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(DOCTOR, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(DOCTOR,contentValues,ID+"="+doctorDataList.get(i).getId(),null);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(user_info, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(user_info,contentValues,"id=?",new String[]{Integer.toString(doctorDataList.get(i).getId())});
            }

            System.out.println(rowID + " : userInfo insert successfully");

        }
        sqLiteDatabase.close();

    }


    public void insertChamber(List<ChamberData> chamberDataList) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in Chamber");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < chamberDataList.size(); i++) {

            contentValues.put(ID, chamberDataList.get(i).getId());
            contentValues.put(doctor_id, chamberDataList.get(i).getDoctorId());
            contentValues.put(chamber_address, chamberDataList.get(i).getChamberAddress());
            contentValues.put(cham_dist, chamberDataList.get(i).getChamDist());
            contentValues.put(cham_upa, chamberDataList.get(i).getChamUpa());
            contentValues.put(contact_no, chamberDataList.get(i).getContactNo());
            contentValues.put(chamber_opens, chamberDataList.get(i).getChamberOpens());
            contentValues.put(status, chamberDataList.get(i).getStatus());


//            rowID = sqLiteDatabase.insert(CHAMBER, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(CHAMBER, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(CHAMBER,contentValues,ID+"="+chamberData.getId(),null);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(CHAMBER, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(CHAMBER,contentValues,"id=?",new String[]{Integer.toString(chamberDataList.get(i).getId())});
            }
            System.out.println(rowID + " : chamber InvestigationData insert successfully");

        }
        sqLiteDatabase.close();

    }

    public void insertRevisedBy(List<RevisedByData> revisedByDataList) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in revised by");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < revisedByDataList.size(); i++) {

            contentValues.put(ID, revisedByDataList.get(i).getId());
            contentValues.put(disease_management_id, revisedByDataList.get(i).getDiseaseManagementId());
            contentValues.put(doctor_id, revisedByDataList.get(i).getDoctorsId());

//            rowID = sqLiteDatabase.insert(REVISED_BY, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(REVISED_BY, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(REVISED_BY,contentValues,ID+"="+revisedByDataList.get(i).getId(),null);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(REVISED_BY, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(REVISED_BY,contentValues,"id=?",new String[]{Integer.toString(revisedByDataList.get(i).getId())});
            }
            System.out.println(rowID + " : revised InvestigationData insert successfully");

        }
        sqLiteDatabase.close();
    }

    public void insertDosage(List<DosageData> dosageData) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < dosageData.size(); i++) {
            contentValues.put(id, dosageData.get(i).getId());
            contentValues.put(medicine_id, dosageData.get(i).getMedicine_id());
            contentValues.put(generic_type, dosageData.get(i).getGeneric_type().trim());
            contentValues.put(specific_type, dosageData.get(i).getSpecific_type().trim());
            contentValues.put(UNIT, dosageData.get(i).getUnit().trim());
            contentValues.put(package_method, dosageData.get(i).getPackage_method().trim());
            contentValues.put(package_price, dosageData.get(i).getPackage_price().trim());
            contentValues.put(unit_price, dosageData.get(i).getUnit_price().trim());
//            rowID =  sqLiteDatabase.insert(MEDICINES_DOSAGE,null,contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(MEDICINES_DOSAGE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(MEDICINES_DOSAGE,contentValues,id+"="+dosageData.get(i).getId(),null);
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(MEDICINES_DOSAGE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(MEDICINES_DOSAGE,contentValues,"_id=?",new String[]{Integer.toString(dosageData.get(i).getId())});
            }
            System.out.println(rowID + " : Dosage InvestigationData insert successfully");

        }

        sqLiteDatabase.close();



    }



    public void insertMedicineSubGroup(List<MedicineSubGroupData> sData) {

      try {
          SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
          ContentValues contentValues = new ContentValues();
          System.out.println("size of msgroup array........ : " + sData.size());
          for (int i = 0; i < sData.size(); i++) {
              contentValues.put(ID, sData.get(i).getId());
              contentValues.put(category, sData.get(i).getCategory());
              contentValues.put(generic_name, sData.get(i).getGenericName());
              contentValues.put(pregnency_category, sData.get(i).getPregnencyCategory());
              contentValues.put(medicine_subgroup_id, sData.get(i).getMedicine_subgroup_id());

              int rowID = (int) sqLiteDatabase.insertWithOnConflict(MEDICINE_SUBGROUP, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            System.out.println(sData.get(i).getId() + " : Medicine  insert successfully :"+sData.get(i).getGenericName());
              if (rowID == -1) {
                  rowID = sqLiteDatabase.update(MEDICINE_SUBGROUP, contentValues, "medicine_subgroup_id=?", new String[]{Integer.toString(sData.get(i).getMedicine_subgroup_id())});
//                System.out.println(sData.get(i).getId() + " : Medicine Sub Group insert successfully :"+sData.get(i).getGenericName());

              }
              System.out.println(sData.get(i).getId() + " : Medicine Sub Group insert successfully :"+sData.get(i).getGenericName()+" "+sData.get(i).getCategory());

          }
          sqLiteDatabase.close();
      }catch (Exception e){
          System.out.println("erooor   ............... : "+e.getMessage());
      }


    }

    public void insertInvestigation(List<InvestigationData> sData) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in investigation");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(name, sData.get(i).getName());
            contentValues.put(normal_finding, sData.get(i).getNormal_finding());
            contentValues.put(increased, sData.get(i).getIncreased());
            contentValues.put(decreased, sData.get(i).getDecreased());
            contentValues.put(others, sData.get(i).getOthers());

//            rowID = sqLiteDatabase.insert(INVESTIGATION, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(INVESTIGATION, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(INVESTIGATION,contentValues,ID+"="+sData.get(i).getId(),null);
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(INVESTIGATION, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

            if(rowID == -1){
                rowID = sqLiteDatabase.update(INVESTIGATION,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : investigation  insert successfully");

        }

        sqLiteDatabase.close();


    }

    public void insertDiseaseManagement(List<DiseaseManageData> sData) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(id, sData.get(i).getId());
            contentValues.put(disease_name, sData.get(i).getDisease_name());
            contentValues.put(branch_id, sData.get(i).getBranch_id());
            contentValues.put(category, sData.get(i).getCategory());
            contentValues.put(patient_category, sData.get(i).getPatient_category());
            contentValues.put(patient_sample, sData.get(i).getPatient_sample());
            contentValues.put(pregnancy, sData.get(i).getPregnancy());
            contentValues.put(age, sData.get(i).getAge());
            contentValues.put(weight, sData.get(i).getWeight());
            contentValues.put(investigation_id, sData.get(i).getInvestigation_id());
            contentValues.put(analysis, sData.get(i).getAnalysis());
            contentValues.put(advice, sData.get(i).getAdvice());
            contentValues.put(history, sData.get(i).getHistory());
            contentValues.put(sign, sData.get(i).getSign());
            contentValues.put(symtom, sData.get(i).getSymtom());
            contentValues.put(on_examination, sData.get(i).getOn_examination());
            contentValues.put(note, sData.get(i).getNote());
            contentValues.put(template_type, sData.get(i).getTemplate_type());
//            rowID = sqLiteDatabase.insert(DISEASE_MANAGEMENT, null, contentValues);

//            rowID = sqLiteDatabase.insertWithOnConflict(DISEASE_MANAGEMENT, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//            rowID = sqLiteDatabase.update(DISEASE_MANAGEMENT,contentValues,id+"="+sData.get(i).getId(),null);
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(DISEASE_MANAGEMENT, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(DISEASE_MANAGEMENT,contentValues,"_id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }

            System.out.println(rowID + " :  disease insert successfully");

        }
        sqLiteDatabase.close();

    }



    public void insertMedicineData(List<MedicineData> medicineData) {
       /* hideLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);*/
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
/*// final ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set body
        progressDialog.show(); // show progress dialog*/
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < medicineData.size(); i++) {
            contentValues.put(ID, medicineData.get(i).getId());
            contentValues.put(name, medicineData.get(i).getName().trim());
            contentValues.put(company_id, String.valueOf(medicineData.get(i).getCompany_id()));
            contentValues.put(medicine_sub_group_id, String.valueOf(medicineData.get(i).getMedicine_sub_group_id()));
            contentValues.put(sub_generic_name, medicineData.get(i).getSub_generic_name().trim());
            contentValues.put(COMPANY_NAME, medicineData.get(i).getCompany_name().trim());
//            rowID =  sqLiteDatabase.insert(MEDICINES,null,contentValues);
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(MEDICINES, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID =  sqLiteDatabase.update(MEDICINES,contentValues,"id=?",new String[]{Integer.toString(medicineData.get(i).getId())});
            }

            System.out.println(rowID + " : MEDICINES insert successfully");

        }
        sqLiteDatabase.close();
//        progressDialog.dismiss();
       /* hideLayout.setVisibility(View.VISIBLE);
        progressLayout.setVisibility(View.GONE);*/


    }
    public void insertContactList(List<Android_Contact> sData) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in only contact");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {


            contentValues.put(name, sData.get(i).getName());
            contentValues.put(number, sData.get(i).getPhone_number());

//            rowID = sqLiteDatabase.insert(CONTACT_LIST, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(CONTACT_LIST, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(CONTACT_LIST, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

            if(rowID == -1){
                rowID = sqLiteDatabase.update(CONTACT_LIST,contentValues,"number=?",new String[]{sData.get(i).getPhone_number()});
            }

            System.out.println(rowID + " : contact_list InvestigationData insert successfully");

        }
        sqLiteDatabase.close();


    }
    public void insertContactListFromMethod(List<ContactData> sData) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in contact_list with method");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {


//            contentValues.put(name, sData.get(i).getName());
            contentValues.put(number, sData.get(i).getPhone());
            contentValues.put(blood_group, sData.get(i).getBloodGroup());


//            rowID = sqLiteDatabase.insert(CONTACT_LIST, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(CONTACT_LIST, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(CONTACT_LIST, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);

            if(rowID == -1){
                rowID = sqLiteDatabase.update(CONTACT_LIST,contentValues,"number=?",new String[]{sData.get(i).getPhone()});
            }

            System.out.println(rowID + " : contact_list from method insert successfully");

        }
        sqLiteDatabase.close();


    }

    public void insertBloodGroup(Android_Contact android_contact) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        System.out.println("Enter in investigation");
        ContentValues contentValues = new ContentValues();
        contentValues.put(number,android_contact.getPhone_number());
//        contentValues.put(blood_group,android_contact.getBloodGroup());

        int rowID = (int) sqLiteDatabase.insertWithOnConflict(CONTACT_LIST, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        if(rowID == -1){
            rowID = sqLiteDatabase.update(CONTACT_LIST,contentValues,"number=?",new String[]{android_contact.getPhone_number()});
        }

      /*  for (int i = 0; i < sData.size(); i++) {

            contentValues.put(ID, i+1);
            contentValues.put(name, sData.get(i).getName());
            contentValues.put(number, sData.get(i).getPhone_number());

//            rowID = sqLiteDatabase.insert(CONTACT_LIST, null, contentValues);
//            rowID = sqLiteDatabase.insertWithOnConflict(CONTACT_LIST, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);



            System.out.println(rowID + " : contact_list InvestigationData insert successfully");

        }*/
        sqLiteDatabase.close();


    }


    public void insertCurrentTime(int id,String currentTime) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
        String UPDATED_DATE = "updated_date";
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(updated_date, currentTime);
//           rowID = sqLiteDatabase.insert(DATA_SYNC, null, contentValues);
//        rowID =  sqLiteDatabase.insertWithOnConflict(DATA_SYNC,null,contentValues, SQLiteDatabase.CONFLICT_IGNORE);
//        rowID = sqLiteDatabase.update(DATA_SYNC,contentValues,ID+"=?",new String[]{id});

//        sqLiteDatabase.update(DATA_SYNC, contentValues, UPDATED_DATE, ID +" = " + getID());

        int rowID = (int) sqLiteDatabase.insertWithOnConflict(DATA_SYNC, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
        if(rowID == -1){
            rowID = sqLiteDatabase.update(DATA_SYNC,contentValues,"id=?",new String[]{Integer.toString(id)});
        }


        sqLiteDatabase.close();
    }


    public int insertSystem(List<SystemData> sData) {


//        System.out.println("Enter in branch...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
           contentValues.put(name,sData.get(i).getName());
           int rowID = (int) sqLiteDatabase.insertWithOnConflict(SYSTEM, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(SYSTEM,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : SYSTEM  INSERT SUCCESSFULLY........... : "+sData.get(i).getName());

        }
        sqLiteDatabase.close();
        return 1;

    }
    public int insertCategory(List<CategoryData> sData) {


//        System.out.println("Enter in branch...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(name,sData.get(i).getName());
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(CATEGORY, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(CATEGORY,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : CATEGORY  INSERT SUCCESSFULLY :"+sData.get(i).getName());

        }
        sqLiteDatabase.close();
        return 1;

    }
    public int insertSystemCategory(List<SystemCategoryData> sData) {


        System.out.println("Enter in system category...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(system_id,sData.get(i).getSystemId());
            contentValues.put(category_id,sData.get(i).getCategoryId());
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(SYSTEM_CATEGORY, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(SYSTEM_CATEGORY,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : SYSTEM_CATEGORY INSERT SUCCESSFULLY...... : "+sData.get(i).getCategoryId());

        }
        sqLiteDatabase.close();
        return 1;

    }

    public void insertSponsor(List<SponsorData> sData) {


        System.out.println("Enter in system category...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(supplier_id,sData.get(i).getSupplierId());
            contentValues.put(rank,sData.get(i).getRank());
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(SPONSOR, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(SPONSOR,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
//            System.out.println(rowID + " : SYSTEM_CATEGORY INSERT SUCCESSFULLY...... : "+sData.get(i).getCategoryId());

        }
        sqLiteDatabase.close();


    }
    public void insertHospital(List<HospitalData> sData) {


        System.out.println("Enter in system hospital...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(name,sData.get(i).getName());
            contentValues.put(contact,sData.get(i).getContact());
            contentValues.put(address,sData.get(i).getAddress());
            contentValues.put(hos_dist,sData.get(i).getHosDist());
            contentValues.put(hos_upa,sData.get(i).getHosUpa());

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(HOSPITAL, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(HOSPITAL,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : SYSTEM_CATEGORY INSERT SUCCESSFULLY...... : "+sData.get(i).getName());

        }
        sqLiteDatabase.close();


    }

    public void insertInvestigationPrice(List<InvestigationPriceData> sData) {


        System.out.println("Enter in system iPrice...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(investigation_id,sData.get(i).getInvestigationId());
            contentValues.put(hospital_id,sData.get(i).getHospitalId());
            contentValues.put(price,sData.get(i).getPrice());
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(INVESTIGATION_PRICE, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(INVESTIGATION_PRICE,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
//            System.out.println(rowID + " : SYSTEM_CATEGORY INSERT SUCCESSFULLY...... : "+sData.get(i).getCategoryId());

        }
        sqLiteDatabase.close();


    }
    public void insertMedicinePharmacology(List<MedicinePharmacologyData> sData) {


        System.out.println("Enter in system mPharma...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(medicine_sub_group_id,sData.get(i).getMedicineSubGroupId());
            contentValues.put(pharmacology_id,sData.get(i).getPharmacologyId());
            contentValues.put(sub_pharmacology_id,sData.get(i).getSubPharmacologyId());
            System.out.println(" man ijjot sesh....................");
            contentValues.put(description, sData.get(i).getDescription());
            System.out.println(" man ijjot sesh.................... 2");
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(MEDICINE_PHARMACOLOGY, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(MEDICINE_PHARMACOLOGY,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : medicine pharmacology INSERT SUCCESSFULLY...... : "+sData.get(i).getId());

        }
        sqLiteDatabase.close();


    }
    public void insertPharmacologyInfo(List<PharmacologyInfoData> sData) {


        System.out.println("Enter in system pInfo..............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(name,sData.get(i).getName());
            contentValues.put(rank,sData.get(i).getRank());
            contentValues.put(sub_pharmacologies_id,sData.get(i).getSubPharmacologiesId());
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(PHARMACOLOGY_INFO, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(PHARMACOLOGY_INFO,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : pharmacology info INSERT SUCCESSFULLY...... : "+sData.get(i).getName());

        }
        sqLiteDatabase.close();


    }
    public void insertSubPharmacology(List<SubPharmacologyData> sData) {


        System.out.println("Enter in system sPharmaccc...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(name,sData.get(i).getName());
            contentValues.put(parent_pharmacologies_id,sData.get(i).getParentPharmacologiesId());
            contentValues.put(rank,sData.get(i).getRank());
            int rowID = (int) sqLiteDatabase.insertWithOnConflict(SUB_PHARMACOLOGY, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(SUB_PHARMACOLOGY,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : sub pharmacology INSERT SUCCESSFULLY...... : "+sData.get(i).getName());

        }
        sqLiteDatabase.close();


    }
    public void insertMedia(List<MediaData> sData) {


        System.out.println("Enter in system sPharmaccc...............................");
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        long rowID = 0;
//        System.out.println("Enter in branch");
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i < sData.size(); i++) {
            contentValues.put(ID, sData.get(i).getId());
            contentValues.put(name,sData.get(i).getName());
            contentValues.put(type,sData.get(i).getType());
            contentValues.put(url,sData.get(i).getUrl());

            int rowID = (int) sqLiteDatabase.insertWithOnConflict(MEDIA, null, contentValues, SQLiteDatabase.CONFLICT_IGNORE);
            if(rowID == -1){
                rowID = sqLiteDatabase.update(MEDIA,contentValues,"id=?",new String[]{Integer.toString(sData.get(i).getId())});
            }
            System.out.println(rowID + " : MEDIA INSERT SUCCESSFULLY...... : "+sData.get(i).getName());

        }
        sqLiteDatabase.close();


    }

   /* private int getMaxId(){
       String query= "select MAX(id) from"+DATA_SYNC+" limit 0 and 1";
    }*/


}
