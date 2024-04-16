CREATE TABLE IF NOT EXISTS boot_log (
  event_id varchar(50) ,
  event_date datetime ,
  thread varchar(255) ,
  class varchar(255) ,
  function varchar(255) ,
  message varchar(255) ,
  level varchar(255) ,
  time datetime 
);