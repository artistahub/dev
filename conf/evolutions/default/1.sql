# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table accounttype (
  id                        varchar(255) not null,
  reference                 varchar(255),
  name                      varchar(255),
  label                     varchar(255),
  create_time               datetime,
  constraint pk_accounttype primary key (id))
;

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

create table comments (
  id                        varchar(255) not null,
  commenter_id              varchar(255),
  description               varchar(255),
  myphoto_id                varchar(255),
  date_created              datetime,
  constraint pk_comments primary key (id))
;

create table feeds (
  id                        varchar(255) not null,
  system_user1_id           varchar(255),
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  constraint pk_feeds primary key (id))
;

create table myphoto (
  id                        varchar(255) not null,
  system_user1_id           varchar(255),
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  tag                       varchar(255),
  constraint pk_myphoto primary key (id))
;

create table organizations (
  id                        varchar(255) not null,
  name                      varchar(255),
  email                     varchar(255),
  www                       varchar(255),
  phone_number              varchar(255),
  address_id                varchar(255),
  billing_address_id        varchar(255),
  shipping_address_id       varchar(255),
  create_time               datetime,
  update_time               datetime,
  constraint pk_organizations primary key (id))
;

create table organizationcategories (
  id                        varchar(255) not null,
  reference                 varchar(255),
  name                      varchar(255),
  label                     varchar(255),
  create_time               datetime,
  constraint pk_organizationcategories primary key (id))
;

create table persons (
  id                        varchar(255) not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  www                       varchar(255),
  phone_number              varchar(255),
  cell                      varchar(255),
  birth_date                datetime,
  gender                    varchar(6),
  address_id                varchar(255),
  billing_address_id        varchar(255),
  shipping_address_id       varchar(255),
  create_time               datetime,
  update_time               datetime not null,
  constraint ck_persons_gender check (gender in ('Male','Female','Other')),
  constraint pk_persons primary key (id))
;

create table personcategories (
  id                        varchar(255) not null,
  reference                 varchar(255),
  name                      varchar(255),
  label                     varchar(255),
  create_time               datetime,
  constraint pk_personcategories primary key (id))
;

create table profileImages (
  id                        varchar(255) not null,
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  image_type                varchar(255),
  status                    varchar(2),
  constraint ck_profileImages_status check (status in ('i','a')),
  constraint pk_profileImages primary key (id))
;

create table profileimagecomments (
  id                        varchar(255) not null,
  commenter_id              varchar(255),
  description               varchar(255),
  my_profile_photo_id       varchar(255),
  date_created              datetime,
  constraint pk_profileimagecomments primary key (id))
;

create table s3file (
  id                        varchar(40) not null,
  bucket                    varchar(255),
  name                      varchar(255),
  constraint pk_s3file primary key (id))
;

create table systemaccount (
  id                        varchar(255) not null,
  create_time               datetime,
  system_user1_id           varchar(255),
  account_type              varchar(8),
  constraint ck_systemaccount_account_type check (account_type in ('free','gold','platinum')),
  constraint pk_systemaccount primary key (id))
;

create table artistas (
  id                        varchar(255) not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  user_name                 varchar(255),
  age                       integer,
  password                  varchar(255),
  email                     varchar(255),
  active_profile_image_id   varchar(255),
  date_created              datetime,
  location_id               varchar(255),
  billing_address_id        varchar(255),
  mailing_address_id        varchar(255),
  test                      varchar(255),
  user_type_id              varchar(255),
  constraint pk_artistas primary key (id))
;

create table usertype (
  id                        varchar(255) not null,
  create_time               datetime,
  reference                 varchar(255),
  name                      varchar(255),
  label                     varchar(255),
  constraint pk_usertype primary key (id))
;

create table videos (
  id                        varchar(255) not null,
  system_user1_id           varchar(255),
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  constraint pk_videos primary key (id))
;


create table organizations_organizationcategories (
  organizations_id               varchar(255) not null,
  organizationcategories_id      varchar(255) not null,
  constraint pk_organizations_organizationcategories primary key (organizations_id, organizationcategories_id))
;

create table persons_personcategories (
  persons_id                     varchar(255) not null,
  personcategories_id            varchar(255) not null,
  constraint pk_persons_personcategories primary key (persons_id, personcategories_id))
;

create table persons_organizations (
  persons_id                     varchar(255) not null,
  organizations_id               varchar(255) not null,
  constraint pk_persons_organizations primary key (persons_id, organizations_id))
;
alter table comments add constraint fk_comments_commenter_1 foreign key (commenter_id) references artistas (id) on delete restrict on update restrict;
create index ix_comments_commenter_1 on comments (commenter_id);
alter table comments add constraint fk_comments_myphoto_2 foreign key (myphoto_id) references myphoto (id) on delete restrict on update restrict;
create index ix_comments_myphoto_2 on comments (myphoto_id);
alter table feeds add constraint fk_feeds_systemUser1_3 foreign key (system_user1_id) references artistas (id) on delete restrict on update restrict;
create index ix_feeds_systemUser1_3 on feeds (system_user1_id);
alter table myphoto add constraint fk_myphoto_systemUser1_4 foreign key (system_user1_id) references artistas (id) on delete restrict on update restrict;
create index ix_myphoto_systemUser1_4 on myphoto (system_user1_id);
alter table organizations add constraint fk_organizations_address_5 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_organizations_address_5 on organizations (address_id);
alter table organizations add constraint fk_organizations_billingAddress_6 foreign key (billing_address_id) references address (id) on delete restrict on update restrict;
create index ix_organizations_billingAddress_6 on organizations (billing_address_id);
alter table organizations add constraint fk_organizations_shippingAddress_7 foreign key (shipping_address_id) references address (id) on delete restrict on update restrict;
create index ix_organizations_shippingAddress_7 on organizations (shipping_address_id);
alter table persons add constraint fk_persons_address_8 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_persons_address_8 on persons (address_id);
alter table persons add constraint fk_persons_billingAddress_9 foreign key (billing_address_id) references address (id) on delete restrict on update restrict;
create index ix_persons_billingAddress_9 on persons (billing_address_id);
alter table persons add constraint fk_persons_shippingAddress_10 foreign key (shipping_address_id) references address (id) on delete restrict on update restrict;
create index ix_persons_shippingAddress_10 on persons (shipping_address_id);
alter table profileimagecomments add constraint fk_profileimagecomments_commenter_11 foreign key (commenter_id) references artistas (id) on delete restrict on update restrict;
create index ix_profileimagecomments_commenter_11 on profileimagecomments (commenter_id);
alter table profileimagecomments add constraint fk_profileimagecomments_myProfilePhoto_12 foreign key (my_profile_photo_id) references profileImages (id) on delete restrict on update restrict;
create index ix_profileimagecomments_myProfilePhoto_12 on profileimagecomments (my_profile_photo_id);
alter table systemaccount add constraint fk_systemaccount_systemUser1_13 foreign key (system_user1_id) references artistas (id) on delete restrict on update restrict;
create index ix_systemaccount_systemUser1_13 on systemaccount (system_user1_id);
alter table artistas add constraint fk_artistas_activeProfileImage_14 foreign key (active_profile_image_id) references profileImages (id) on delete restrict on update restrict;
create index ix_artistas_activeProfileImage_14 on artistas (active_profile_image_id);
alter table artistas add constraint fk_artistas_location_15 foreign key (location_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_location_15 on artistas (location_id);
alter table artistas add constraint fk_artistas_BillingAddress_16 foreign key (billing_address_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_BillingAddress_16 on artistas (billing_address_id);
alter table artistas add constraint fk_artistas_MailingAddress_17 foreign key (mailing_address_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_MailingAddress_17 on artistas (mailing_address_id);
alter table artistas add constraint fk_artistas_userType_18 foreign key (user_type_id) references usertype (id) on delete restrict on update restrict;
create index ix_artistas_userType_18 on artistas (user_type_id);
alter table videos add constraint fk_videos_systemUser1_19 foreign key (system_user1_id) references artistas (id) on delete restrict on update restrict;
create index ix_videos_systemUser1_19 on videos (system_user1_id);



alter table organizations_organizationcategories add constraint fk_organizations_organizationcategories_organizations_01 foreign key (organizations_id) references organizations (id) on delete restrict on update restrict;

alter table organizations_organizationcategories add constraint fk_organizations_organizationcategories_organizationcategorie_02 foreign key (organizationcategories_id) references organizationcategories (id) on delete restrict on update restrict;

alter table persons_personcategories add constraint fk_persons_personcategories_persons_01 foreign key (persons_id) references persons (id) on delete restrict on update restrict;

alter table persons_personcategories add constraint fk_persons_personcategories_personcategories_02 foreign key (personcategories_id) references personcategories (id) on delete restrict on update restrict;

alter table persons_organizations add constraint fk_persons_organizations_persons_01 foreign key (persons_id) references persons (id) on delete restrict on update restrict;

alter table persons_organizations add constraint fk_persons_organizations_organizations_02 foreign key (organizations_id) references organizations (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table accounttype;

drop table address;

drop table comments;

drop table feeds;

drop table myphoto;

drop table organizations;

drop table persons_organizations;

drop table organizations_organizationcategories;

drop table organizationcategories;

drop table persons;

drop table persons_personcategories;

drop table personcategories;

drop table profileImages;

drop table profileimagecomments;

drop table s3file;

drop table systemaccount;

drop table artistas;

drop table usertype;

drop table videos;

SET FOREIGN_KEY_CHECKS=1;

