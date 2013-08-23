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
  user_id                   varchar(255),
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  constraint pk_feeds primary key (id))
;

create table myphoto (
  id                        varchar(255) not null,
  user_id                   varchar(255),
  url                       varchar(255),
  description               varchar(255),
  date_created              datetime,
  tag                       varchar(255),
  constraint pk_myphoto primary key (id))
;

create table profileImages (
  id                        varchar(255) not null,
  url                       varchar(255),
  user_id                   varchar(255),
  description               varchar(255),
  date_created              datetime,
  status                    varchar(2),
  constraint ck_profileImages_status check (status in ('i','a')),
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
  active_profile_image_id   varchar(255),
  date_created              datetime,
  location_id               varchar(255),
  billing_address_id        varchar(255),
  mailing_address_id        varchar(255),
  test                      varchar(255),
  actor_type                varchar(3),
  constraint ck_artistas_actor_type check (actor_type in ('p','ag','a')),
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

alter table comments add constraint fk_comments_commenter_1 foreign key (commenter_id) references artistas (id) on delete restrict on update restrict;
create index ix_comments_commenter_1 on comments (commenter_id);
alter table comments add constraint fk_comments_myphoto_2 foreign key (myphoto_id) references myphoto (id) on delete restrict on update restrict;
create index ix_comments_myphoto_2 on comments (myphoto_id);
alter table feeds add constraint fk_feeds_user_3 foreign key (user_id) references artistas (id) on delete restrict on update restrict;
create index ix_feeds_user_3 on feeds (user_id);
alter table myphoto add constraint fk_myphoto_user_4 foreign key (user_id) references artistas (id) on delete restrict on update restrict;
create index ix_myphoto_user_4 on myphoto (user_id);
alter table profileImages add constraint fk_profileImages_user_5 foreign key (user_id) references artistas (id) on delete restrict on update restrict;
create index ix_profileImages_user_5 on profileImages (user_id);
alter table artistas add constraint fk_artistas_activeProfileImage_6 foreign key (active_profile_image_id) references profileImages (id) on delete restrict on update restrict;
create index ix_artistas_activeProfileImage_6 on artistas (active_profile_image_id);
alter table artistas add constraint fk_artistas_location_7 foreign key (location_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_location_7 on artistas (location_id);
alter table artistas add constraint fk_artistas_BillingAddress_8 foreign key (billing_address_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_BillingAddress_8 on artistas (billing_address_id);
alter table artistas add constraint fk_artistas_MailingAddress_9 foreign key (mailing_address_id) references address (id) on delete restrict on update restrict;
create index ix_artistas_MailingAddress_9 on artistas (mailing_address_id);
alter table videos add constraint fk_videos_user_10 foreign key (user_id) references artistas (id) on delete restrict on update restrict;
create index ix_videos_user_10 on videos (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table address;

drop table comments;

drop table feeds;

drop table myphoto;

drop table profileImages;

drop table artistas;

drop table videos;

SET FOREIGN_KEY_CHECKS=1;

