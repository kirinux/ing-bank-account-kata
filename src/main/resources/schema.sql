/*==============================================================*/
/* DROP TABLE                                                   */
/*==============================================================*/

DROP TABLE IF EXISTS CLIENT CASCADE;
DROP TABLE IF EXISTS ACCOUNT CASCADE;
DROP TABLE IF EXISTS TRANSACTION CASCADE;

/*==============================================================*/
/* DROP SEQUENCE                                                   */
/*==============================================================*/

DROP SEQUENCE IF EXISTS seq_client_ID_SEQ;
DROP SEQUENCE IF EXISTS seq_account_ID_SEQ;
DROP SEQUENCE IF EXISTS seq_transaction_id_seq ;

/*==============================================================*/
/* Table: CLIENT                                         */
/*==============================================================*/
CREATE TABLE client (
    id_client integer NOT NULL,
    lastname character varying(20) NOT NULL,
    firstname character varying(20) NOT NULL,
    birth_date timestamp without time zone NOT NULL,
	CONSTRAINT PK_CLIENT PRIMARY KEY (id_client)

);


/*==============================================================*/
/* Table: ACCOUNT                                         */
/*==============================================================*/
CREATE TABLE account (
    id_account integer NOT NULL,
    account_number character varying(20) NOT NULL,
    balance float NOT NULL,
	ID_CLIENT integer NOT NULL,
	CONSTRAINT PK_ACCOUNT PRIMARY KEY (id_account)

 );
 
  alter table ACCOUNT
   add constraint FK_CLIENT foreign key (ID_CLIENT)
      references CLIENT (id_client)
      on update restrict
      on delete restrict;
 
 
/*==============================================================*/
/* Table: TRANSACTION                                         */
/*==============================================================*/
CREATE TABLE transaction (
    id_transaction integer NOT NULL,
    label character varying(20) NOT NULL,
    value float NOT NULL,
    date_transaction TIMESTAMP NOT NULL,
	ID_ACCOUNT integer NOT NULL,
	ID_CLIENT integer NOT NULL,
	type character varying(20) NOT NULL,
	CONSTRAINT PK_TRANSACTION PRIMARY KEY (id_transaction)

 );
  
 alter table TRANSACTION
   add constraint FK_ACCOUNT foreign key (ID_ACCOUNT)
      references ACCOUNT (id_account)
      on update restrict
      on delete restrict;
      
  alter table TRANSACTION
   add constraint FK_CLIENT_TRANSACTION foreign key (ID_CLIENT)
      references CLIENT (id_client)
      on update restrict
      on delete restrict;
 
 
CREATE SEQUENCE seq_client_ID_SEQ
   START WITH 1
   INCREMENT BY 1
   NO MINVALUE
   NO MAXVALUE
   CACHE 1;
    
CREATE SEQUENCE seq_account_ID_SEQ
   START WITH 1
   INCREMENT BY 1
   NO MINVALUE
   NO MAXVALUE
   CACHE 1;
    
CREATE SEQUENCE seq_transaction_ID_SEQ
   START WITH 4
   INCREMENT BY 1
   NO MINVALUE
   NO MAXVALUE
   CACHE 1;

   INSERT INTO public.client(id_client, lastname, firstname, birth_date) VALUES (1, 'Martin', 'Gabriel','19-05-1986');
   INSERT INTO public.client(id_client, lastname, firstname, birth_date) VALUES (2, 'Robert', 'Julien','04-09-1973');

   
   INSERT INTO public.account(id_account, account_number, balance, id_client) VALUES (1, '123', 2593.5, 1);
   INSERT INTO public.account(id_account, account_number, balance, id_client) VALUES (2, '365.45', 20500.13, 2);

   
   INSERT INTO public.transaction(id_transaction, label, value, date_transaction, id_account, id_client, type) VALUES (1, 'retrait', 20, '12-12-2021', 1, 1,  'diposit');
   INSERT INTO public.transaction(id_transaction, label, value, date_transaction, id_account, id_client, type) VALUES (2, 'retrait', 200, '10-12-2021', 1, 1, 'diposit');
   INSERT INTO public.transaction(id_transaction, label, value, date_transaction, id_account, id_client, type) VALUES (3, 'retrait', 15, '10-12-2021', 1, 2,  'withdraw');

