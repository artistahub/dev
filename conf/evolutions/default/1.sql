# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        varchar(255) not null,
  address                   varchar(255),
  city                      varchar(255),
  state                     varchar(255),
  zip                       varchar(255),
  country                   varchar(255),
  date_created              datetime,
  constraint pk_address primary key (id))
;

create table profileImages (
  id                        varchar(255) not null,
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  constraint pk_profileImages primary key (id))
;

create table artistas (
  id                        varchar(255) not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  user_name                 varchar(255),
  age                       integer,
  password                  varchar(255),
  email                     varchar(255),
  profile_image_id          varchar(255),
  date_created              datetime,
  location_id               varchar(255),
  billing_address_id        varchar(255),
  mailing_address_id        varchar(255),
  constraint pk_artistas primary key (id))
;

create table videos (
  id                        varchar(255) not null,
  user_id                   varchar(255),
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  constraint pk_videos primary key (id))
;

alter table artistas add constraint fk_artistas_profileImage_1 foreign key (profile_image_id) references profileImages (id) on delete restrict on update restrict;
create index ix_artistas_profileImage_1 on artistas (profile_image_id);
alter table artistas add constraint fk_artistas_location_2 foreign key (location_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_location_2 on artistas (location_id);
alter table artistas add constraint fk_artistas_BillingAddress_3 foreign key (billing_address_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_BillingAddress_3 on artistas (billing_address_id);
alter table artistas add constraint fk_artistas_MailingAddress_4 foreign key (mailing_address_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_MailingAddress_4 on artistas (mailing_address_id);
alter table videos add constraint fk_videos_user_5 foreign key (user_id) references artistas (id) on delete restrict on update restrict;
create index ix_videos_user_5 on videos (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table address;

drop table profileImages;

drop table artistas;

drop table videos;

SET FOREIGN_KEY_CHECKS=1;

