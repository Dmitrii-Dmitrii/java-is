    create table public.cats (
        id integer not null,
        owner_id integer,
        birth_date timestamp(6),
        breed varchar(255),
        color varchar(255) check (color in ('WHITE','BLACK','GINGER','BROWN','CREAM','BEIGE','YELLOW')),
        name varchar(255),
        primary key (id)
    );

    create table public.owners (
        id integer not null,
        birth_date timestamp(6),
        name varchar(255),
        primary key (id)
    );

    create sequence cats_seq start with 1 increment by 1;

    create sequence owners_seq start with 1 increment by 1;

    create table cat_friends (
        cat_id integer not null,
        friend_id integer not null
    );

    alter table if exists public.cats 
       add constraint FKc0phghv1jwbvelwan7pndwrei 
       foreign key (owner_id) 
       references public.owners;

    alter table if exists cat_friends 
       add constraint FKllxmeubbkoyiera02l1sufxaa 
       foreign key (friend_id) 
       references public.cats;

    alter table if exists cat_friends 
       add constraint FKa9lcucexim3nxtot0h9q00g0m 
       foreign key (cat_id) 
       references public.cats;

    create table public.cats (
        id integer not null,
        owner_id integer,
        birth_date timestamp(6),
        breed varchar(255),
        color varchar(255) check (color in ('WHITE','BLACK','GINGER','BROWN','CREAM','BEIGE','YELLOW')),
        name varchar(255),
        primary key (id)
    );

    create table public.owners (
        id integer not null,
        birth_date timestamp(6),
        name varchar(255),
        primary key (id)
    );

    create sequence cats_seq start with 1 increment by 1;

    create sequence owners_seq start with 1 increment by 1;

    create table cat_friends (
        cat_id integer not null,
        friend_id integer not null
    );

    alter table if exists public.cats 
       add constraint FKc0phghv1jwbvelwan7pndwrei 
       foreign key (owner_id) 
       references public.owners;

    alter table if exists cat_friends 
       add constraint FKllxmeubbkoyiera02l1sufxaa 
       foreign key (friend_id) 
       references public.cats;

    alter table if exists cat_friends 
       add constraint FKa9lcucexim3nxtot0h9q00g0m 
       foreign key (cat_id) 
       references public.cats;
