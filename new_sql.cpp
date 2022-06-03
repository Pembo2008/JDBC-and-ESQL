#include <string>
#include <iostream>
#include <pqxx/pqxx>
#include <windows.h>
#pragma execution_character_set( "utf-8" )

using namespace std;
using namespace pqxx;

//pqxx::connection C = new connection("dbname = postgres user = postgres password = ABCabc123 hostaddr = 127.0.0.1 port = 5432")
std::string connectionString = "dbname = postgres user = postgres password = ABCabc123 hostaddr = 127.0.0.1 port = 5432";
pqxx::connection conn(connectionString.c_str());
std::string connectionString2 = "dbname = postgres user = guest password = guestpwd hostaddr = 127.0.0.1 port = 5432";
pqxx::connection conn2(connectionString2.c_str());

void createDB_1(int rl) {

    string db_name, tb_name;
    std::cout << "input database name:";
    cin >> db_name;
    std::cout << "input table name:";
    string sql1, sql2;
    cin >> tb_name;
    sql1 = "create database " + db_name + " owner postgres;";
    sql2 = "CREATE TABLE IF NOT EXISTS " + tb_name +
        " (ИДЕНТИФИКАТОР INT PRIMARY KEY check(ИДЕНТИФИКАТОР>0),"
        + "МЕД_ПЕРСОНАЛ_1 INT NOT NULL,"
        + " ВОЗРАСТ INT NOT NULL);";
    if (rl == 2)
    {
        nontransaction N(conn2);
        N.exec(sql1);
        N.commit();
        std::string connectionString3 = "dbname = " + db_name + " user = guest password = guestpwd hostaddr = 127.0.0.1 port = 5432";
        pqxx::connection conn3(connectionString3.c_str());
        work W(conn3);
        W.exec(sql2);
        W.commit();
    }
    else
    {
    nontransaction N(conn);
    N.exec(sql1);
    N.commit();
    std::string connectionString3 = "dbname = " + db_name + " user = postgres password = ABCabc123 hostaddr = 127.0.0.1 port = 5432";
    pqxx::connection conn3(connectionString3.c_str());
    work W(conn3);
    W.exec(sql2);
    W.commit();
    }
    std::cout << "Database and table created successfully" << endl;

}


void deleteDB_2(int rl) {

    string db_name;
    std::cout << "input database name:";
    cin >> db_name;
    string sql;
    sql = "drop database IF EXISTS " + db_name + ";";
    if (rl == 2)
    {
        nontransaction N(conn2);
        N.exec(sql);
        N.commit();
    }
    else
    {
        nontransaction N(conn);
        N.exec(sql);
        N.commit();
    }
    std::cout << "Database dropped successfully" << endl;
}


void clearTB_3(int rl) {

    string tb_name;
    std::cout << "input table name:";
    cin >> tb_name;
    string sql;
    sql = "select clearTab(" + tb_name + ");";
    if (rl == 2)
    {
        nontransaction N(conn2);
        N.exec(sql);
        N.commit();
    }
    else
    {
        nontransaction N(conn);
        N.exec(sql);
        N.commit();
    }
    std::cout << "Table cleared successfully" << endl;
}


void addToTB_4(int rl) {
    int tb_name;
    std::cout << "input 1 for МЕДПЕРСОНАЛ:"<<endl;
    std::cout << "input 2 for new:" << endl;
    std::cout << "input 3 for МЕСТО_РАБОТЫ:" << endl;
    std::cout << "input 4 for ТИПЫ_ОПЕРАЦИЙ:" << endl;
    std::cout << "input 5 for ТРУДОВАЯ_ДЕЯТЕЛЬНОСТЬ:" << endl;
    std::cout << "input table num:" << endl;
    cin >> tb_name;
    int id_m, tax_amount, id_n, med_pers_id, age, id_p, tax_percent, id_t, savings, price, tax_sum, \
        id_c, med_id, place_id, op_id, amount, payment;
    string surname, adress, sql, hospital, adress_p, op, op_p, date;
    switch (tb_name)
    {
    case 1:
        std::cout << "Input id: ";
        cin >> id_m;
        std::cout << "Input surname: ";
        cin >> surname;
        std::cout << "Input adress: ";
        cin >> adress;
        std::cout << "Input tax amount: ";
        cin >> tax_amount;
        sql = "select addToTableStaff(" + to_string(id_m) + "," + to_string(surname) + "," + to_string(adress) 
            + "," + to_string(tax_amount) + ");";
        break;

    case 2:
        std::cout << "Input id: ";
        cin >> id_n;
        std::cout << "Input medpersonal id: ";
        cin >> med_pers_id;
        std::cout << "Input age: ";
        cin >> age;
        sql = "select addToTableNew(" + to_string(id_n) + "," + to_string(med_pers_id) + "," + to_string(age) + ");";
        break;

    case 3:
        std::cout << "Input id: ";
        cin >> id_p;
        std::cout << "Input hospital name: ";
        cin >> hospital;
        std::cout << "Input adress: ";
        cin >> adress_p;
        std::cout << "Input tax percent: ";
        cin >> tax_percent;
        sql = "select addToTablePlace(" + to_string(id_p) + "," + hospital + "," + adress_p + "," + to_string(tax_percent) + ");";
        break;
    case 4:
        std::cout << "Input id: ";
        cin >> id_t;
        std::cout << "Input name of operation: ";
        cin >> op;
        std::cout << "Input place of operation: ";
        cin >> op_p;
        std::cout << "Input savings: ";
        cin >> savings;
        std::cout << "Input price: ";
        cin >> price;
        std::cout << "Input sum of tax: ";
        cin >> tax_sum;
        sql = "select addToTableOper(" + to_string(id_t) + "," + op + "," + op_p + "," + to_string(savings) + "," + \
            to_string(price) + "," + to_string(tax_sum) + ");";
        break;
    case 5:
        std::cout << "Input contract id: ";
        cin >> id_c;
        std::cout << "Input date : ";
        cin >> date;
        std::cout << "Input med id: ";
        cin >> med_id;
        std::cout << "Input place_id: ";
        cin >> place_id;
        std::cout << "Input operation id: ";
        cin >> op_id;
        std::cout << "Input amount: ";
        cin >> amount;
        std::cout << "Input payment: ";
        cin >> payment;
        sql = "select addToTableAct(" + to_string(id_c) + "," + date + "," + to_string(med_id) + "," + \
            to_string(place_id) + "," + to_string(op_id) + "," + to_string(amount) + "," + to_string(payment) + ");";
        break;
    }
    if (rl == 2)
    {
        work W(conn2);
        W.exec(sql);
        W.commit();
    }
    else
    {
        work W(conn);
        W.exec(sql);
        W.commit();
    }
    std::cout << "Table updated successfully" << endl;

}


void searchTB_5(int rl) {
    std::cout << "input 1 for МЕДПЕРСОНАЛ:" << endl;
    std::cout << "input 2 for МЕСТО_РАБОТЫ:" << endl;
    std::cout << "input 3 for ТИПЫ_ОПЕРАЦИЙ:" << endl;
    std::cout << "input 4 for ТРУДОВАЯ_ДЕЯТЕЛЬНОСТЬ:" << endl;
    int tb_name;
    string field, value;
    std::cout << "input tb num:";
    cin >> tb_name;
    std::cout << "Input field to search: ";
    cin >> field;
    std::cout << "Input value to search: ";
    cin >> value;

    string sql;
    if (tb_name == 1) {
        sql = "select searchMed(" + field + "," + value + ");";
        work W(conn);
        result R(W.exec(sql));
        for (size_t i = 0; i < R.size(); i++) {
            std::cout << "row: " << R[i][0];
        }
    }
    else if (tb_name == 2) {
        std::cout << 1;
        sql = "select searchPlace(" + field + "," + value + ");";
        work W(conn);
        result R(W.exec(sql));
        for (size_t i = 0; i < R.size(); i++) {
            std::cout << "row: " << R[i][0];
        }
    }
    else if (tb_name == 3) {
        sql = "select searchOp(" + field + "," + value + ");";
        work W(conn);
        result R(W.exec(sql));
        for (size_t i = 0; i < R.size(); i++) {
            std::cout << "row: " << R[i][0];
        }
    }
    if (tb_name == 4) {
        sql = "select searchTd(" + field + "," + value + ");";
        work W(conn);
        result R(W.exec(sql));
        for (size_t i = 0; i < R.size(); i++) {
            std::cout << "row: " << R[i][0];
        }
    }
    std::cout << "Operation done successfully" << endl;
}


void updateTB_6(int rl) {
    int tb_name;
    std::cout << "input table name:"<<endl;
    std::cout << "input 1 for МЕДПЕРСОНАЛ:" << endl;
    std::cout << "input 2 for МЕСТО_РАБОТЫ:" << endl;
    std::cout << "input 3 for ТИПЫ_ОПЕРАЦИЙ:" << endl;
    std::cout << "input 4 for ТРУДОВАЯ_ДЕЯТЕЛЬНОСТЬ:" << endl;
    std::cout << "input table num:" << endl;
    cin >> tb_name;
    string sql, surname, adress, hospital, adress_p, op, op_p, date;
    int id_m, tax_amount, id_p, tax_percent, id_t, savings, price, tax_sum, id_c, med_id, place_id, op_id, amount, payment;
    switch (tb_name)
    {
    case 1:
        std::cout << "Input id: ";
        cin >> id_m;
        std::cout << "Input surname: ";
        cin >> surname;
        std::cout << "Input adress: ";
        cin >> adress;
        std::cout << "Input tax amount: ";
        cin >> tax_amount;
        sql = "select update_staff(" + to_string(id_m) + "," + surname + "," + adress + "," + to_string(tax_amount) + ");";
        break;

    case 2:
        std::cout << "Input id: ";
        cin >> id_p;
        std::cout << "Input hospital name: ";
        cin >> hospital;
        std::cout << "Input adress: ";
        cin >> adress_p;
        std::cout << "Input tax percent: ";
        cin >> tax_percent;
        sql = "select update_place(" + to_string(id_p) + "," + hospital + "," + adress_p + "," + to_string(tax_percent) + ");";
        break;
    case 3:
        std::cout << "Input id: ";
        cin >> id_t;
        std::cout << "Input name of operation: ";
        cin >> op;
        std::cout << "Input place of operation: ";
        cin >> op_p;
        std::cout << "Input savings: ";
        cin >> savings;
        std::cout << "Input price: ";
        cin >> price;
        std::cout << "Input sum of tax: ";
        cin >> tax_sum;
        sql = "select update_op(" + to_string(id_t) + "," + op + "," + op_p + "," + to_string(savings) + "," + \
            to_string(price) + "," + to_string(tax_sum) + ");";
        break;
    case 4:
        std::cout << "Input contract id: ";
        cin >> id_c;
        std::cout << "Input date : ";
        cin >> date;
        std::cout << "Input med id: ";
        cin >> med_id;
        std::cout << "Input place_id: ";
        cin >> place_id;
        std::cout << "Input operation id: ";
        cin >> op_id;
        std::cout << "Input amount: ";
        cin >> amount;
        std::cout << "Input payment: ";
        cin >> payment;
        sql = "select update_td(" + to_string(id_c) + "," + date + "," + to_string(med_id) + "," + \
            to_string(place_id) + "," + to_string(op_id) + "," + to_string(amount) + "," + to_string(payment) + ");";
        break;
    }
    if (rl == 2)
    {
        work W(conn2);
        W.exec(sql);
        W.commit();
    }
    else
    {
        work W(conn);
        W.exec(sql);
        W.commit();
    }
    std::cout << "Table updated successfully" << endl;

}


void dropTB_7(int rl) {

    string tb_name, field, value;
    std::cout << "input table name:";
    cin >> tb_name;
    std::cout << "input field name:";
    cin >> tb_name;
    std::cout << "input value name:";
    cin >> tb_name;
    string sql;
    sql = "select dropFunc( " + tb_name + "," + field + "," + value + ");";
    if (rl == 2)
    {
        work W(conn2);
        W.exec(sql);
        W.commit();
    }
    else
    {
        work W(conn);
        W.exec(sql);
        W.commit();
    }
    std::cout << "rows deleted successfully" << endl;
}


void addRole_8(int rl) {

    int role = 2;

    while (true) {
        std::cout << "Input int for the role to create: 1 - for admin, 0 - for the guest: " << endl;
        cin >> role;
        if ((role == 0) || (role == 1)) {
            break;
        }
        else {
            std::cout << role + " is not 0 or 1" << endl;
        }
    }
    string sql;
    if (role == 0) {
        sql = "select createGuest();";
    }
    else {
        sql = "select createAdmin();";
    }
    if (rl == 2)
    {
        work W(conn2);
        W.exec(sql);
        W.commit();
    }
    else
    {
        work W(conn);
        W.exec(sql);
        W.commit();
    }
    std::cout << "Role created" << endl;
}


int main(int argc, char* argv[]) {
    //SetConsoleCP(1251);
    //SetConsoleOutputCP(1251);
    //setlocale(LC_ALL, "");
    SetConsoleOutputCP(65001);
    int rl1;
    cout << "Choose role: 1 - for admin, 2 - for guest: ";
    cin >> rl1;
    try {
        if (conn.is_open()) {
            std::cout << "Opened database successfully: " << conn.dbname() << std::endl;
        }
        else {
            std::cout << "Can't open database" << std::endl;
            return 1;
        }
        std::cout << "Connection ready" << endl;
        std::cout << "Choose 1 for creating database:" << endl;
        std::cout << "Choose 2 to drop database:" << endl;
        std::cout << "Choose 3 for clear table:" << endl;
        std::cout << "Choose 4 for adding new row to table:" << endl;
        std::cout << "Choose 5 for searching table by string:" << endl;
        std::cout << "Choose 6 to update table by string:" << endl;
        std::cout << "Choose 7 to delete table:" << endl;
        std::cout << "Choose 8 to add role:" << endl;
        std::cout << "Choose 0 to leave program:" << endl;
        int command = 9;
        while (command != 0) {
            std::cout << "input your command:";
            cin >> command;
            switch (command) {
            case 1:
                try {
                    createDB_1(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 2:
                try {
                    deleteDB_2(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 3:
                try {
                    clearTB_3(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 4:
                try {
                    addToTB_4(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 5:
                try {
                    searchTB_5(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 6:
                try {
                    updateTB_6(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 7:
                try {
                    dropTB_7(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 8:
                try {
                    addRole_8(rl1);
                }
                catch (const std::exception& e) {
                    std::cerr << e.what() << std::endl;
                }
                break;
            case 0:
                std::cout << "Leaving program";
                break;
            default:
                std::cout << "Wrong input, try again";
                break;
            }
        }


        conn.close();
    }
    catch (const std::exception& e) {
        cerr << e.what() << std::endl;
        return 1;
    }
}