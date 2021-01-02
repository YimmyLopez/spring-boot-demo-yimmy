  alter table contact 
       add constraint FK_Company_Contact 
       foreign key (company_id) 
       references company


